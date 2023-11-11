import pandas as pd
from urlemt import UrlEMT
class BiciMad:
    def __init__(self, month: int, year: int):
        self._month = month
        self._year = year
        self._data = self.get_data(month, year)

    @staticmethod
    def get_data(month: int, year: int) -> pd.DataFrame:
        uerlmt = UrlEMT()
        url_emt = uerlmt.get_csv(month,year)
        df = pd.read_csv(url_emt,delimiter=';',index_col='unlock_date',encoding='utf-8', parse_dates=['unlock_date',
                                                                                                'lock_date'],usecols=[ 'idBike', 'fleet', 'trip_minutes', 'geolocation_unlock', 'address_unlock', 'unlock_date', 'locktype', 'unlocktype', 'geolocation_lock', 'address_lock', 'lock_date', 'station_unlock',
    'unlock_station_name', 'station_lock', 'lock_station_name'])
        return df

    @property
    def data(self):
        return self._data

    def __str__(self):
        return str(self._data)

    def clean(self):
        df = self._data.dropna(how='all')
        df['fleet'].astype(str)
        df['idBike'].astype(str)
        df['station_lock'].astype(str)
        df['station_unlock'].astype(str)
        return df

    def resume(self) -> pd.Series:
        totalUsosMes = BiciMad.total_usage_month(self._data)
        totalHoraMes = BiciMad.total_time(self._data)
        popularSatations = BiciMad.most_popular_stations(self._data)
        usesFromMostPopular = BiciMad.usage_from_most_popular_station(self._data)

        valores = [totalUsosMes,totalHoraMes,popularSatations,usesFromMostPopular]
        dfSerie = pd.Series(valores,index = ['total_uses', 'total_time', 'most_popular_station', 'uses_from_most_popular'])
        
     
    @staticmethod
    def total_usage_month(df) -> pd.Series:
        df.index = pd.to_datetime(df.index)
        total_usos = df.groupby('idBike').size()
        total_usos = total_usos.sum()
        return total_usos
    
    def total_time(df) -> pd.Series:
        horasDias = df.groupby(df.index.date)['trip_minutes'].sum() / 60
        horasMes = horasDias.sum()
        return round(horasMes)
    
    def most_popular_stations(df):
        stations = df.groupby('address_unlock').count()
        most_pop = stations.sort_values(by='idBike',ascending = False)
        return most_pop
    
    def usage_from_most_popular_station(df) -> int:
        stations = df.groupby('address_unlock').count()
        most_pop = stations.sort_values(by='idBike',ascending = False)
        most_pop = most_pop['idBike']
        return most_pop.iloc[0]
    
classBici = BiciMad()
respect_nigga = classBici.get_data()
print(respect_nigga)