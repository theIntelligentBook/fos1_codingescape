"use strict";

let ping = () => Commands.ping();

let right = (i) => Commands.right(i);
let down = (i) => Commands.down(i);

let canGoRight = () => Commands.canGoRight()
let canGoDown = () => Commands.canGoDown()

let look = (d) => Commands.look(d)
let lookRight = () => Commands.look(0)
let lookLeft = () => Commands.look(2)
let lookUp = () => Commands.look(3)
let lookDown = () => Commands.look(1)

let ownDistance = () => Commands.look(-1)

let move = (d) => Commands.move(d)