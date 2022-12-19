# from bs4 import BeautifulSoup
# from urllib.request import urlopen
#
# def crawlGitCommit(gitID, commits): #String , int
#     response = urlopen("https://github.com/" + gitID)
#     soup = BeautifulSoup(response, "html.parser")
#
#     value = soup.find("h2", {"class" : "f4 text-normal mb-2"})
#     value = int(value.text.strip().split("\n")[0])
#
#     if value > commits:
#         print("new commited")
#
#     else:
#         print("still the same")
#         pass

# crawlGitCommit("sungjun4403", 442)

def test(a, b):
    return a+b