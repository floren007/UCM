import requests
import re
from io import TextIOWrapper, BytesIO, StringIO
from zipfile import ZipFile
import pandas as pd
from loguru import logger
import zipfile
class UrlEMT:
    # Creamos 2 constantes
    EMT = 'https://opendata.emtmadrid.es/'
    GENERAL = "/Datos-estaticos/Datos-generales-(1)"
    # se Crea el init pero como el enunciado dice que este vacio
    def __init__(self):
        # creo un atributo llamado valid_urls 
        # que llama a la funcion select_valid_urls
        self.enlaces_validos = self.select_valid_urls()
        #  Esta funcion solo   agarrar los links que  son   validos

    @staticmethod
    def get_links(html_text):
        # Use regular expressions to find valid links
        link_pattern = r'<a[^>]*href="(/getattachment/[^"]+/trips_\d{2}_\d{2}_\w+-csv\.aspx)"[^>]*>.*?</a>'
        # un conjunto es una lista de objetos unicos por lo tanto el set() es la mejor opcion
        valid_links = set(re.findall(link_pattern, html_text))
        return valid_links


    def get_url(self, year, month):
        # como solo son validos  los meses que son entre 1,12  y  el  año entre 2021  y 2023
        # pues hago un rango entre el año y los meses que nos interesan, so no enncuentra el mes ni el año
        # pues es que la persona introducio mal el mes y el año
        if month in range(1,13) and year in range(21,24):
            target_month_name = pd.Timestamp(year, month, 1).strftime("%B")
            pattern = f'/trips_{str(year)}_{str(month).zfill(2)}_{target_month_name}-csv.aspx'
            for url in self.enlaces_validos:
                if re.search(pattern, url):
                    print(f"Coincidió esta URL: {url}")
                    # testeo
                    url1 = self.EMT+url
        else:
            ValueError("No has introducido el año y el mes correcto")
        return url1
        
    @staticmethod
    def select_valid_urls():
        try:
            # obtenemos el enlace de la web de bicimadrid
            response = requests.get(UrlEMT.EMT + UrlEMT.GENERAL)
            # si la respuesta de
            if response.status_code == 200:
                html_text = response.text
                valid_links = UrlEMT.get_links(html_text)
                
                
            else:
                raise ConnectionError("Conexion fallida")
            return valid_links
        except ConnectionError as e:
            e

    

    def get_csv(self, year, month) -> StringIO:
        url = self.get_url(year, month)
        csv = self.csv_from_zip(url)
        return csv
        try:
            response = requests.get(self.EMT+url)
            if response.status_code != 200:
                raise ConnectionError("Failed to connect to EMT server.")
            else:
                zip_file = ZipFile(BytesIO(response.content))
                # Assume there's only one CSV file in the ZIP
                csv_filename = zip_file.namelist()[0]
                csv_file = zip_file.open(csv_filename)
                return StringIO(csv_file)
        except ConnectionError as e:
            e
    @staticmethod
    def csv_from_zip(url: str) -> StringIO:
        try:
            file = requests.get(url)
            file.raise_for_status()
            file_content =  BytesIO(file.content)
            with zipfile.ZipFile(file_content) as zp:
                listar = zp.namelist()
                string_csv = StringIO(listar[0])
            zp.close()
        except ConnectionError as e:
            logger.info(e)
        return string_csv

            
            


emt_url = UrlEMT()
year = 23
month = 2

try:
    csv_file = emt_url.get_csv(year, month)
    df = pd.read_csv(csv_file)
    print(df.head())  # Realizar operaciones con el DataFrame
except ValueError as e:
    e