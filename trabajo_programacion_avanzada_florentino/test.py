import requests
import re
import zipfile
from io import TextIOWrapper
from bs4 import BeautifulSoup



def select_valid_urls():
    """
    Actualiza el conjunto de enlaces válidos de la clase y devuelve el conjunto.
    Lanza una excepción de tipo ConnectionError si el código de respuesta no es 200.
    """
    try:
        response = requests.get('https://opendata.emtmadrid.es//Datos-estaticos/Datos-generales-(1)')
        response.raise_for_status()
        html_text = response.text
        soup = BeautifulSoup(html_text, 'html.parser')
        find_ul = soup.find("ul", {"class": "ficheros"})
        array = []
        find_all_li = find_ul.find('a').get('href')
        array.append(find_all_li)
        print(array)
        # enlaces = self.get_links(html_text)
        # self._enlaces_validos = enlaces
        #return enlaces
    except requests.exceptions.RequestException as e:
        raise ConnectionError("Error de conexión: " + str(e))
select_valid_urls()