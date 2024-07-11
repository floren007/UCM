import pandas as pd
from .urlemt import UrlEMT
class BiciMad:
    """
    La funcion init sirve para inicializar los atributos de la clase, en este caso me interesa actualizar
    los enlaces validos que recoje de la web.
    Los atributos year, month y data son privados
    """
    def __init__(self, year: int, month: int):
        self._year = year
        self._month = month
        self._data = BiciMad.get_data(year, month)

    """
    Esta funcion devuelve los datos del csv que nosotros le pasamos por parametros con el mes y año.
    Ojo nos lo devuelve en formato StringIO y aqui es donde se pasa a formato pandas Dataframe

    >>> pd.Dataframe
    """
    @staticmethod
    def get_data(year: int, month: int) -> pd.DataFrame:
        # creamos una instancia de la clase 
        uerlmt = UrlEMT()
        # llamamos a la funcion get_csv para que haga todo el proceso del script urlemt
        url_emt = uerlmt.get_csv(year,month)
        # leemos los datos devueltos en pandas dataframe y nos quedamos con lo que nos interesa
        df = pd.read_csv(url_emt,delimiter=';',index_col='fecha',encoding='utf-8', parse_dates=['fecha','unlock_date',
                                                                                                'lock_date'],usecols=['fecha','idBike', 'fleet', 'trip_minutes', 'geolocation_unlock', 'address_unlock', 'unlock_date', 'locktype', 'unlocktype', 'geolocation_lock', 'address_lock', 'lock_date', 'station_unlock',
        'unlock_station_name', 'station_lock', 'lock_station_name'])
        # devolvemos el dataftame como tal
        return df

    """
    Esta funcion sirve para actualizar la variable data
    """
    @property
    def data(self):
        return self._data

    def __str__(self):
        return str(self._data)

    """
    Esta funcion sirve para limpiar el dataframe de valores nulos y ademas casteamos algunas columnas a string

    >>> pd.Dataframe
    """
    def clean(self):
        # se hace la respectiva limpieza del dataframe
        # ponemos el dropnan y se eliminan os null
        df = self._data.dropna(how='all')
        # casteamos las columnas a string
        df['fleet'] = df['fleet'].astype(str)
        df['fleet'] = df['idBike'].astype(str)
        df['station_lock'] = df['station_lock'].astype(str)
        df['station_unlock'] = df['station_unlock'].astype(str)
       

    """
    Esta funcion sirve para sacar un resumen de las consultas de bicimad

    >>> pd.Serie
    """
    def resume(self) -> pd.Series:
        # le pasamos a las distintas funciones los datos del csv
        
        # Esta funcion funciona correctamente
        totalUsosMes = BiciMad.total_usage_month(self._data)
       
        totalHoraMes = self._data['trip_minutes'].sum()/60

        popularSatations = BiciMad.most_popular_stations(self._data)

        usesFromMostPopular = BiciMad.usage_from_most_popular_station(self._data)

        # creo un array con los valores que quiero introducir en la serie
        valores = [self._year,self._month,totalUsosMes,totalHoraMes,popularSatations,usesFromMostPopular]
        # se crea la serie con los valores expuestos anteriormente y con el indice
        dfSerie = pd.Series(valores,index = ['year','month','total_uses', 'total_time', 'most_popular_station', 'uses_from_most_popular'])
        # se retorna la serie
        return dfSerie
    
    
    """
    Esta funcion sirve para sacar el uso total de bicis en el mes

    >>> int
    """
    @staticmethod
    def total_usage_month(df) -> pd.Series:
        # se agrupan las fechas
        total_usos = df.groupby(df.index.date).size()
        # se suman y sacamos el numero
        total = total_usos.sum()
        total_usos = int(total)
        return total_usos
    
    """
    Esta funcion calcula las horas de uso total en el mes

    >>> int
    """
    """
    Esta funcion saca las estaciones mas utilizadas en el mes

    >>> set()
    """
    def most_popular_stations(df: pd.DataFrame) -> pd.Series:
        # se agrupa por lock_station_name y se cuenta el numero de estaciones
        estaciones_usos = df['lock_station_name'].value_counts()
        listado = estaciones_usos.head()
        return set(listado.index)   
    
    """
    Esta funcion sirve para sacar el maximo de viajes que se ha echo en una estacion en el mes

    >>> int
    """
    def usage_from_most_popular_station(df) -> int:
        # se agrupan las estaciones y se cuenta el numero de viajes
        estaciones_usos = df['lock_station_name'].value_counts()
        listado = estaciones_usos.head()
        return set(listado.values)
    

