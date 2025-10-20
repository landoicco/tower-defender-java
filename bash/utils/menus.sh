#!/bin/bash

###################################################
# Utility methods for showing a "menu" to the user
###################################################

# Show script headline
show_headline() {
echo_text_bgreen \
"
================================
======== Builder Script ========
================================
"
}

# Show quick menu
show_quick_menu() {
    echo_text_bold "Quick options: "
    echo_text_yellow "clean, build, play, bplay, install"
    echo -n "For more options, type "
    echo_text_yellow "help"
    echo
}

# Show long menu
show_long_menu() {
    # deps
    echo -ne "\tList modules and show dependency graphs - "
    echo_text_yellow "deps"

    # clean
    echo -ne "\tClean all previous builds - "
    echo_text_yellow "clean"

    # build
    echo -ne "\tCompile code and pack modules - "
    echo_text_yellow "build"

    # bplay
    echo -ne "\tBuild source code and play game - "
    echo_text_yellow "bplay"

    # install
    echo -ne "\tCreate executable of this game - "
    echo_text_yellow "install"

    # help
    echo -ne "\tList all available commands - "
    echo_text_yellow "help"

    # play
    echo -ne "\tPlay game - "
    echo_text_yellow "play"
}