from bicimad.urlemt import UrlEMT
import io
import requests

def test_constantes_clase():
    assert UrlEMT.EMT == 'https://opendata.emtmadrid.es/'
    assert UrlEMT.GENERAL == "/Datos-estaticos/Datos-generales-(1)"

def test_url_get_links():
    class_emt = UrlEMT()
    test_valido = """<a target="_blank" 
    href="/getattachment/34b933e4-4756-4fed-8d5b-2d44f7503ccc/trips_22_12_December-csv.aspx" """
    url_valida = class_emt.get_links(test_valido)
    assert url_valida == {"/getattachment/34b933e4-4756-4fed-8d5b-2d44f7503ccc/trips_22_12_December-csv.aspx"}

def test_csv_from_zip():
    class_emt = UrlEMT()
    url_correcta = {"https://opendata.emtmadrid.es//getattachment/34b933e4-4756-4fed-8d5b-2d44f7503ccc/trips_22_12_December-csv.aspx"}
    csv = class_emt.csv_from_zip(url_correcta)
    assert type(csv) is io.StringIO

def test_get_csv():
    class_emt = UrlEMT()
    csv = class_emt.get_csv(22,12)
    assert type(csv) is io.StringIO

def test_get_url():
    class_emt = UrlEMT()
    url_correcta = {"/trips_22_12"}
    url_valida = class_emt.get_url(url_correcta)
    assert url_valida == {"/trips_22_12"}

def test_select_valid_urls():
    class_emt = UrlEMT()
    class_emt.select_valid_urls()
    comprobar = requests.get(UrlEMT.EMT + UrlEMT.GENERAL)
    assert comprobar.status_code == 200