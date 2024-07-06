Simple_Calculator

Created by Mitchell Seitz, July 2023.

Copyright July 2023 - Present. 

Android Studio Project Folder: Simple_Calculator

.APK File for testing: Simple_Calculator.apk
__________________________________________________________________________

Description:

This is my first android app with a realistic use case, a scientific 
calculator I made while taking COMP2161 at TRU. With the permission 
of Musfiq Rahman, My professor in that course, I am sharing this as 
a portfolio project. 

There isn't much to say about this one - It's a scientific calculator
with a light/dark mode. It shows the completed operations at the top,
followed buy the latest value, and has trig functions, deg/rad, pos/
negative numbers, etc. 

The documentation inside the program is somewhat sparce, as my prof 
preferred to have documentation submitted as a separate document
alongside other things related to learning.
__________________________________________________________________________

A basic overview of how the program works is:

0: The user can toggle system states using the light/dark 
button, rad/deg button, or store a value M for later use 
using the set M button. The listener functions for these in 
MainActivity.java set the relevant variables and update the 
view as needed.

1: The user puts their input in using the number buttons, the 
trig functions buttons, pi or saved value buttons, decimal buttons, 
etc, and the program stores the input as a string that is added to for 
each input given by the listeners for these buttons.

The clear button removes current value and function input, and 
the clear all resets the whole equation. The +/- button can be 
used to change the sign of the input.

2: When the user clicks equals or a button for another operation, 
the program checks if the input is valid, and if it is, it calls 
the evaluate() method, which applies the necessary operation and 
displays the output. 
__________________________________________________________________________

Some things I could improve if I were going to re-visit this program:

1: Add exponents & roots. This would be similar to using the trig functions, 
but would check for previous inputs and values. 

2: Add some type of value restriction. I didn't bother with restricting 
what values can be put into the calculator, but it might be worth looking 
into max/min values. 

3: Add inverse trig functions

4: Add the ability to input fractions, as well as display output as well 
formatted fractions. 

Doing all of these things would make this a good replacement for most 
existing scientific calculators for most students.
