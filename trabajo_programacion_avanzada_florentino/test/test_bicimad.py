from bicimad.bicimad import BiciMad
import pandas as pd
def test_init():
    bici = BiciMad(month=12,year=22)
    assert bici._month == 12
    assert bici._year == 22
    tipo_fecha = bici._data['idBike'].dtype
    assert bici._data.dtypes is pd.DataFrame
    assert tipo_fecha is int
    assert bici._data['lock_station_name'].dtype is str
    assert bici._data['dock_lock'].dtype is int
    assert bici._data['geolocation_unlock'].dtype is dict
    
def test_get_data():
    assert BiciMad.get_data(12,22) is pd.DataFrame

def test_data():
    bici = BiciMad(month=12,year=22)
    #assert bici.data.shape == 
