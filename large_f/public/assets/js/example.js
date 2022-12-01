let PATH = '../../file/file.txt'

// const path = require('../../../node_modules/path')

// path.format(
//     {
        
//     });

// console.log(path.extname(PATH))

const fs = require('fs')
const content = "contetnetonvonoenetoetn"
// const fp = require('filepath')

// fs.rename(PATH, '../../file/file.html')
// fp.create


const { exec } = require('child_process')

var RenameCommand = 'mv ../../file/file.txt ../../file/file.html'
var createTxtCommand = 'touch ../../file/file.txt'


exec(RenameCommand, (err, output) => {
    console.log(output)
});

exec(createTxtCommand, (err, output) => {
    console.log(output)
});

exec('echo "modifffffffy file" >> ../../file/file.txt', (err, output) => {
    console.log(output)
});