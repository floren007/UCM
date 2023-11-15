from bicimad import BiciMad
import pandas as pd
import pytest
import io
"""
En este test, compruebo que los parametros son los correctos
y ademas
que algunas columnas tienen el tipo de dato correcto.
"""
def test_testeo():
    pass
def test_init():
    bici = BiciMad(month=12,year=22)
    assert bici.__dict__['_month'] == 12
    assert bici.__dict__['_year'] == 22
    df = bici.__dict__['_data'].dtype
    assert df is pd.DataFrame
    assert type(bici._data) is io.StringIO
    assert bici._data['lock_station_name'].dtype is str
    assert bici._data['dock_lock'].dtype is int
    assert bici._data['geolocation_unlock'].dtype is dict
    
"""
Este test comprubea que la funcion resume devuelve un pandas Series
"""
def test_type_resume():
    bici = BiciMad(month=12,year=22)
    assert type(bici.resume) is pd.Series
"""
En esta funcion compruebo que las dimensiones del dataframe son correctas
"""
def test_data():
    bici = BiciMad(month=12,year=22)
    assert bici.data.shape == "(493140, 14)"

"""
En esta funcion compruebo que el dataframe ya no tiene valores nulos
"""
def test_data_is_null():
    bici = BiciMad(month=12,year=22)
    biciNull=bici.clean()
    assert biciNull.isnull().sum() == 0
"""
En esta funcion se comprueba que el tipo de dato que devuelve es un pandas dataframe
"""
def test_get_data():
    isDF = BiciMad.get_data(month=12,year=22)
    assert type(isDF) is pd.DataFrame

"""
En esta funcion se comprueba que el tipo de dato que devuelve la funcion total_time es un pandas Series
"""
def test_total_time():
    bici = BiciMad(month=12,year=22)
    assert type(bici.total_time) is pd.Series