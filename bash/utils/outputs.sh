#!/bin/bash

##############################################################
# Utility methods for formating colorful outputs to the user
##############################################################

# Make text bold
text_bold() {
echo -en "\e[1m$1\e[0m"   
}
# Make text yellow
text_yellow() {
    echo -e "\e[33m$1\e[0m"
}

text_green() {
    echo -e "\e[32m$1\e[0m"
}

text_orange() {
    echo -e "\033[38;5;214m$1\033[0m"   
}

# Make text bold and green
text_bgreen() {
    echo -e "\e[1;32m$1\e[0m"
}

# Make text bold and yellow
text_byellow() {
    echo -e "\e[1;33m$1\e[0m"
}

# Make text bold and red
text_bred() {
    echo -e "\033[1;31m$1\033[0m"
}

text_borange() {
    echo -e "\033[1;38;5;214m$1\033[0m"   
}