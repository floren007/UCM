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
        # Implement your data extraction logic here
        pass

    @property
    def data(self):
        return self._data

    def __str__(self):
        return str(self._data)

    def clean(self):
        # Implement your data cleaning logic here
        pass

    def resume(self) -> pd.Series:
        # Implement your data summarization logic here
        pass
