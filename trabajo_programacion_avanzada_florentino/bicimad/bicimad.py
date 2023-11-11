import pandas as pd
from urlemt import UrlEMT
class BiciMad:
    def __init__(self, month: int, year: int):
        self._month = month
        self._year = year
        self._data = self.get_data(month, year)

    @staticmethod
    def get_data(month: int, year: int) -> pd.DataFrame:
        # creamos una instancia de la clase 
        uerlmt = UrlEMT()
        # llamamos a la funcion get_csv para que haga todo el proceso del script urlemt
        url_emt = uerlmt.get_csv(month,year)
        # leemos los datos devueltos en pandas dataframe y nos quedamos con lo que nos interesa
        df = pd.read_csv(url_emt,delimiter=';',index_col='unlock_date',encoding='utf-8', parse_dates=['unlock_date',
                                                                                                'lock_date'],usecols=[ 'idBike', 'fleet', 'trip_minutes', 'geolocation_unlock', 'address_unlock', 'unlock_date', 'locktype', 'unlocktype', 'geolocation_lock', 'address_lock', 'lock_date', 'station_unlock',
    'unlock_station_name', 'station_lock', 'lock_station_name'])
        # devolvemos el dataftame como tal
        return df

    @property
    def data(self):
        return self._data

    def __str__(self):
        return str(self._data)

    def clean(self):
        # se hace la respectiva limpieza del dataframe
        # ponemos el dropnan y se eliminan os null
        df = self._data.dropna(how='all')
        # casteamos las columnas a string
        df['fleet'].astype(str)
        df['idBike'].astype(str)
        df['station_lock'].astype(str)
        df['station_unlock'].astype(str)
        return df

    def resume(self) -> pd.Series:
        # le pasamos a las distintas funciones los datos del csv
        totalUsosMes = BiciMad.total_usage_month(self._data)
        totalHoraMes = BiciMad.total_time(self._data)
        popularSatations = BiciMad.most_popular_stations(self._data)
        usesFromMostPopular = BiciMad.usage_from_most_popular_station(self._data)
        # creo un array con los valores que quiero introducir en la serie
        valores = [self._year,self._month,totalUsosMes,totalHoraMes,popularSatations,usesFromMostPopular]
        # se crea la serie con los valores expuestos anteriormente y con el indice
        dfSerie = pd.Series(valores,index = ['year','month','total_uses', 'total_time', 'most_popular_station', 'uses_from_most_popular'])
        # se retorna la serie
        return dfSerie
     
    @staticmethod
    def total_usage_month(df) -> pd.Series:
        # hay que castear la fecha a datetime
        # posteriormente lo pasamos a index 
        df.index = pd.to_datetime(df.index)
        # hacemos un groupby de las bicis que existen y el numero de bicis
        total_usos = df.groupby(df.index.date).size()
        # por ultimo lo sumamos
        total = total_usos.sum()
        total_usos = int(total)
        # hacemos un return del total de usos
        return total_usos
    
    def total_time(df) -> pd.Series:
        # agrupar las fehcas para saca las horas
        horasDias = df.groupby(df.index.date)['trip_minutes'].sum() / 60
        # sumamos la horas
        horasMes = horasDias.sum()
        # se hace un return redoando para que salga entero
        return round(horasMes)
    
    def most_popular_stations(df) -> pd.Series:
        # se agrupa por address_unlock y se cuenta el numero de estaciones
        stations = df.groupby('address_unlock').sum()
        most_pop = stations.index.max()
        most_popular_stations = stations[stations == most_pop].index
        return set(most_popular_stations)
    
    def usage_from_most_popular_station(df) -> int:
        stations = df.groupby('address_unlock').count()
        maximo = stations.max()
        most_pop = stations.sort_values(by='idBike',ascending = False)
        most_pop = most_pop[most_pop == maximo].index
        return int(maximo)
    
classBici = BiciMad(22,12)

x = classBici.resume()
print(x)