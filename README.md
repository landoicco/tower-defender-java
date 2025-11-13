# Java Tower Defender

## How to play?
This project includes a shell utility for building all the code.
To use it, just follow along:

First, make the contents of `bash/` executable. Take a look at those files, are full of long commands that works just like a hand-made *Maven*.
```
chmod +x bash/*
```
move to `bash/`
```
cd bash/
```

and then, just run:
```
./init.sh
```
and the wizard will get all ready for you.

If you want it right now, go for it:

``` shell
./init.sh bplay
```
This will build the *engine* modules (commons, tools & core) and the *demo* module, then run the game.

## How was made?

First of all, the roots of this project are based on the great work of [Kaarin Gaming](https://www.youtube.com/watch?v=kclnyiXmY7Q&list=PL4rzdwizLaxb0-TajNIp5DOoT_PAxhx0T)

The intention of this project is to first be a fun learning project, this is the first purpose. The second one, is to be a useful tools to develop other games of this style.

## What about `bash/`?

Well, Maven/Gradle is for the weak... right?

This project pretends to help understand how the "roots" of Java work, from the very basics and in the most "pure" and "vanilla" way.

That said, NO FRAMWORKS, NO BUILDING TOOLS, NO LIBRARIES

since this is a very basic project, we don't need those.

Want to be sure? Run:

``` shell
./init.sh deps
```
this will show you the modules that make the game, as well as their dependencies.

