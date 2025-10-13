#!/bin/bash

###################################################
# Utility methods for showing a "menu" to the user
###################################################

# Show script headline
show_headline() {
text_bgreen \
"
================================
======== Builder Script ========
================================
"
}

# Show quick menu
show_quick_menu() {
    text_bold "Quick options: "
    text_yellow "clean, build, play, bplay, install"
    echo -n "For more options, type "
    text_yellow "help"
    echo
}

# Show long menu
show_long_menu() {
    # deps
    echo -ne "\tList modules and show dependency graphs - "
    text_yellow "deps"

    # clean
    echo -ne "\tClean all previous builds - "
    text_yellow "clean"

    # build
    echo -ne "\tCompile code and pack modules - "
    text_yellow "build"

    # bplay
    echo -ne "\tBuild source code and play game - "
    text_yellow "bplay"

    # install
    echo -ne "\tCreate executable of this game - "
    text_yellow "install"

    # help
    echo -ne "\tList all available commands - "
    text_yellow "help"

    # play
    echo -ne "\tPlay game - "
    text_yellow "play"
}