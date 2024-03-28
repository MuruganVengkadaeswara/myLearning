React is popular JS library maintained by Facebook 
used to build UI

widely used for Web apps and mobile apps


React allows user to create reusable components help make large apps easier to maintain and manage


React creates virutal DOM
when state changes in components it first runs 'diffing' algorithm
to identify the changes in VDOM

step 2 : reconciliation -> updates the DOM with results of diff



React components : 
===================
    * React apps are made out of components
    * Component is a piece of UI which has its own appearance and functionality

    MyButton /> starts with a capital letter. That’s how you know it’s a React component

    React component names must always start with a capital letter, while HTML tags must be lowercase.   



JSX :
=====

    * JSX is syntax extenstion to javascript
    * It lets you write HTML code in JS
    * stricter than HTML and have to write closing tags for </br>

    *  Your component also can’t return multiple JSX tags. You have to wrap them into a shared parent, like a <div>...</div> or an empty <>...</>


Conditional Rendering:
======================

