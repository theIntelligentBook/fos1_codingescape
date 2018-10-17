"use strict";

window.ping = () => Commands.ping();

window.right = (i) => Commands.right(i);
window.down = (i) => Commands.down(i);
window.left = (i) => Commands.left(i);
window.up = (i) => Commands.up(i);

window.canGoRight = () => Commands.canGoRight()
window.canGoDown = () => Commands.canGoDown()

window.look = (d) => Commands.look(d)
window.lookRight = () => Commands.look(0)
window.lookLeft = () => Commands.look(2)
window.lookUp = () => Commands.look(3)
window.lookDown = () => Commands.look(1)

window.ownDistance = () => Commands.look(-1)

window.move = (d) => Commands.move(d)