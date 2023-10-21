"""
Program to analize the logs files of an apache server.
An example of file acommpanies this file: access.log
"""

def get_user_agent(line: str) -> str:
            user_agent = line.split('"')[5]
            return user_agent
"""
Get the user agent of the line.
Expamples
---------
>>> get_user_agent('66.249.66.35 - - [15/Sep/2023:00:18:46 +0200] "GET /~luis/sw05-06/libre_m2_baja.pdf HTTP/1.1" 200 5940849 "-" "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)"')
'Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)'
>>> get_user_agent('147.96.46.52 - - [10/Oct/2023:12:55:47 +0200] "GET /favicon.ico HTTP/1.1" 404 519 "https://antares.sip.ucm.es/" "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/117.0"')
'Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/117.0'
"""

def is_bot(line: str) -> bool:
    if "Bot" in line or "bot" in line:
        return True
    else:
        return False
            
    '''
    Check of the access in the line correspons to a bot

    Examples
    --------
    >>> is_bot('147.96.46.52 - - [10/Oct/2023:12:55:47 +0200] "GET /favicon.ico HTTP/1.1" 404 519 "https://antares.sip.ucm.es/" "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/117.0"')
    False

    >>> is_bot('66.249.66.35 - - [15/Sep/2023:00:18:46 +0200] "GET /~luis/sw05-06/libre_m2_baja.pdf HTTP/1.1" 200 5940849 "-" "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)"')
    True

    >>> is_bot('213.180.203.109 - - [15/Sep/2023:00:12:18 +0200] "GET /robots.txt HTTP/1.1" 302 567 "-" "Mozilla/5.0 (compatible; YandexBot/3.0; +http://yandex.com/bots)"')
    True
    '''

def get_ipaddr(line: str) -> str:
    ipaddress = line[0:line.find(" ")]
    return ipaddress 
    '''
    Gets the IP address of the line

    >>> get_ipaddr('213.180.203.109 - - [15/Sep/2023:00:12:18 +0200] "GET /robots.txt HTTP/1.1" 302 567 "-" "Mozilla/5.0 (compatible; YandexBot/3.0; +http://yandex.com/bots)"')
    '213.180.203.109'

    >>> get_ipaddr('147.96.46.52 - - [10/Oct/2023:12:55:47 +0200] "GET /favicon.ico HTTP/1.1" 404 519 "https://antares.sip.ucm.es/" "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/117.0"')
    '147.96.46.52'
    '''


def get_hour(line: str) -> int:
            hour = line.split(":")[1]
            return hour
  
"""
Get the user agent of the line.

Expamples
---------
>>> get_hour('66.249.66.35 - - [15/Sep/2023:00:18:46 +0200] "GET /~luis/sw05-06/libre_m2_baja.pdf HTTP/1.1" 200 5940849 "-" "Mozilla/5.0 (compatible; Googlebot/2.1; +http://www.google.com/bot.html)"')
0

>>> get_hour('147.96.46.52 - - [10/Oct/2023:12:55:47 +0200] "GET /favicon.ico HTTP/1.1" 404 519 "https://antacres.sip.ucm.es/" "Mozilla/5.0 (X11; Ubuntu; Linux x86_64; rv:109.0) Gecko/20100101 Firefox/117.0"')
12
"""



def histbyhour(filename: str) -> dict[int, int]:
    # while waiting to understand the statement
    # Luis could explain what i have to do in this function, cause in the other example above, 
    # you wrote examples except in this funciton
    '''
    Computes the histogram of access by hour
    '''
    raise NotImplementedError()

def ipaddreses(filename: str) -> set[str]:
    '''
    Returns the IPs of the accesses that are not bots
    '''
    arrayNobot = []
    with open(filename, 'r') as file:
        for line in file:
            if "Bot" in line or "bot" in line:
               continue
            else:
               arrayNobot.append(line)
    file.close()
    return arrayNobot
      
  



import doctest

def test_doc():
    doctest.run_docstring_examples(get_user_agent, globals(), verbose=True)
    doctest.run_docstring_examples(is_bot, globals(), verbose=True)
    doctest.run_docstring_examples(get_ipaddr, globals(), verbose=True)
    doctest.run_docstring_examples(get_hour, globals(), verbose=True)


def test_ipaddresses():
    assert ipaddreses('access_short.log') == {'34.105.93.183', '39.103.168.88'}

def test_hist():
    hist = histbyhour('access_short.log')
    assert hist == {5: 3, 7: 2, 23: 1}


with open("access_short.log") as file:
    for line in file:
        get_user_agent(line)
        is_bot(line)
        get_ipaddr(line)
        get_hour(line)
        # histbyhour(line)
        ipaddreses(line)
file.close()

textOfile = input("Name of file txt you want to read: \n")
print(ipaddreses(textOfile))