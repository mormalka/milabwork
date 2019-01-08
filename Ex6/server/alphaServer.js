"use strict";

const ALPHA_KEY = "D35A5OUY1LCXI8UV";
const alpha = require('alphavantage')({ key: ALPHA_KEY});
const express = require('express');
const app = express();


const server = require('http').createServer(app);
const io = require('socket.io')(server);
const port = process.env.PORT || 3000;

server.listen(port, function () {
	console.log('Server listening at port %d', port);
});


app.use(express.static(__dirname + '/public'));


io.on('connection', function (socket) {
    // when the client emits 'stock type', this listens and executes
    socket.on('stock type', function (type) {
        console.log('io recognize event');
        setInterval(() => {
            alpha.data.batch([`${type}`]).then(data => {
            let value = `${data['Stock Quotes'][0]['2. price']}`;
            console.log(`${type} -> ${data['Stock Quotes'][0]['2. price']}`);
            //console.dir(data);

            // returns the price value of the selected share subject
		    socket.broadcast.emit('stock type', { 
                price : value
            });

            }).catch(err => {
                console.error("Error: " + err);
            });
        
        //calls every 15 sec
        }, 15000);

    });
});





