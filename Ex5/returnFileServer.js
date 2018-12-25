const express = require('express'); 
const path = require('path'); 
const fs = require('fs');

let app = express();

let dir = path.join(__dirname, './files');

let mime = {
    html: 'text/html',
    txt: 'text/plain',
    css: 'text/css',
    gif: 'image/gif',
    jpg: 'image/jpeg',
    png: 'image/png',
    svg: 'image/svg+xml',
    js: 'application/javascript'
};
app.get('/files/:filename', function (req, res) {
  let file = path.join(dir, req.params.filename);
  if (!fs.existsSync(file)) { //file does not exists
      return res.status(403).end('Forbidden');
  }
  let type = mime[path.extname(file).slice(1)] || 'text/plain';
  let s = fs.createReadStream(file);
  s.on('open', function () { 
      res.set('Content-Type', type);
      s.pipe(res);
      console.log('File display');
  });
  s.on('error', function () {
      res.set('Content-Type', 'text/plain');
      res.status(404);
      res.end('Not found');
  });
});

app.listen(8080, function () {
  console.log('Listening on http://localhost:8080/');
});