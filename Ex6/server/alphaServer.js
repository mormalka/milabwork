"use strict";

const ALPHA_KEY = "D35A5OUY1LCXI8UV";
const alpha = require('alphavantage')({ key: ALPHA_KEY});
const express = require('express');
const app = express();


const server = require('http').createServer(app);
const io = require('socket.io')(server);


io.on('connection', function (socket) {
    // when the client emits 'share sub', this listens and executes
    socket.on('stock type', function (inc) {

        setInterval(() => {
            alpha.data.batch([`${type}`]).then(data => {
            let ss = `${type}`; 
            let value = `${data['Stock Quotes'][0]['2. price']}`;
            console.log(`${type} -> ${data['Stock Quotes'][0]['2. price']}`);
            //console.dir(data);

            // returns the price value of the selected share subject
		    socket.broadcast.emit('stock type', { 
                stock type : ss
                price value : value
             });

            }).catch(err => {
                console.error("Error: " + err);
            });
        
        //calls every 15 sec
        }, 15000);


    });
});

server.listen(3000);



