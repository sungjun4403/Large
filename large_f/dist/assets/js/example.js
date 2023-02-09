let PATH = '../../file/file.txt'

const content = "contetnetonvonoenetoetn"
// const fp = require('filepath')

// fs.rename(PATH, '../../file/file.html')
// fp.create


import { exec } from 'child_process'

var RenameCommand = 'mv ../../file/file.txt ../../file/file.html'
var createTxtCommand = 'touch ../../file/file.txt'


exec(RenameCommand, (err, output) => {
    console.log(output)
});

exec(createTxtCommand, (err, output) => {
    console.log(output)
});

exec('echo "modify file" >> ../../file/file.txt', (err, output) => {
    console.log(output)
});