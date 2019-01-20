const ALPHA_KEY = "D35A5OUY1LCXI8UV";
const alpha = require('alphavantage')({ key: ALPHA_KEY});
const express = require('express');
const app = express();


const server = require('http').createServer(app);
const io = require('socket.io')(server);
const port = process.env.PORT || 3000;

//initial fireBase 

const bodyParser = require('body-parser');
const FCM = require('fcm-push');
const FCM_SERVER_KEY = 'AIzaSyDumkI2JDIdCiLQX1LfoJ9YoGVvuGQu3-8';

let fcm = new FCM(FCM_SERVER_KEY);
let tokens = {}; 
app.use(bodyParser.json());


//socket.io server
server.listen(port, function () {
	console.log('Server is listening at port %d', port);
});
//Socket io implementation while app is alive 
io.on('connection', function (socket) {
    console.log('The client is on connetion');
    // when the client emits 'stock type', this listens and executes
    socket.on('stock type', function (type) {
        console.log('client requested stock type');
        setInterval(() => {
            alpha.data.batch([`${type}`]).then(data => {
            let value = `${data['Stock Quotes'][0]['2. price']}`;
            console.log(`${type} -> ${data['Stock Quotes'][0]['2. price']}`);
            //console.dir(data);
		    io.emit('stock type', { 
                price: value
            });

            }).catch(err => {
                console.error("Error: " + err);
            });
        
        //calls every 15 sec
        }, 15000);

    });
});