from bicimad import BiciMad
import pandas as pd
def test_init():
    bici = BiciMad(month=12,year=22)
    assert bici.__dict__['_month'] == 12
    assert bici.__dict__['_year'] == 22
    df = bici.__dict__['_data'].dtype
    assert bici._data.dtypes is pd.DataFrame
    assert df is pd.DataFrame
    assert bici._data['lock_station_name'].dtype is str
    assert bici._data['dock_lock'].dtype is int
    assert bici._data['geolocation_unlock'].dtype is dict
    

def test_data():
    bici = BiciMad(month=12,year=22)
    assert bici.data.shape == "(493140, 14)"
def test_type_resume():
    bici = BiciMad(month=12,year=22)
    assert bici.resume is pd.Series
def test_data_is_null():
    bici = BiciMad(month=12,year=22)
    bici.clean()
    assert bici._data.isnull().sum() == 0
    
def test_get_data():
    assert BiciMad.get_data(month=12,year=22) is pd.DataFrame

def test_total_time():
    bici = BiciMad(month=12,year=22)
    assert bici.total_time is pd.Series