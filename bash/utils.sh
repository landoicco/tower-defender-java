#!/bin/bash

##################################################
# Utility methods for formating outputs strings
##################################################

# Make text bold
text_bold() {
echo -en "\e[1m$1\e[0m"   
}
# Make text yellow
text_yellow() {
    echo -e "\e[33m$1\e[0m"
}

# Make text bold and green
text_bgreen() {
    echo -e "\e[1;32m$1\e[0m"
}

# Make text bold and yellow
text_byellow() {
    echo -e "\e[1;33m$1\e[0m"
}