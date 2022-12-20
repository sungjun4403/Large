from flask import Flask

from bs4 import BeautifulSoup
from urllib.request import urlopen

app = Flask(__name__)

@app.route('/')
def hello_world():
    return 'hello_world'

@app.route('/checkIfUpdated/<gitID>/<commits>')
def check_if_updated(gitID, commits):
    commits = int(commits)
    return crawl_git_commit(gitID, commits)

def crawl_git_commit(gitID, commits): #String , int
    response = urlopen("https://github.com/" + gitID)
    soup = BeautifulSoup(response, "html.parser")

    value = soup.find("h2", {"class" : "f4 text-normal mb-2"})
    value = int(value.text.strip().split("\n")[0])

    print(value, commits)
    if value > commits: #commits: Past, value: Present
        # return True #UPDATED
        return "True"

    elif value < commits: #커밋이 감소할 수는 없으니까
        return "error"

    elif value == commits:
        # return False #UPDATED NON DETECTED
        return "False"

if __name__ == '__main__':
    app.run(host="127.0.0.1", port=5000)