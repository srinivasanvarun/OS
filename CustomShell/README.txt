# Assignment 1
---------------------------------------------------------------------

A simple C program to implement custom shell with built in commands:
	* about -> prints the team members details
	* dir -> displays the current directory
	* clr -> clear the prompt screen
The shell also works with IO redirections and pipelines

---------------------------------------------------------------------
Output:
-------
Varuns-MacBook-Pro:CustomShell varunsrinivasan$ ./shell
Welcome to the Shell implemented in C.
# about
=====================================================
This custom shell is created by the following people
Varun Srinivasan	W1351349
Sharon Subathran	W1358689
=====================================================
# cd
Current working dir: /Users/varunsrinivasan/Documents/coding/C/CustomShell
# dir
Current working dir: /Users/varunsrinivasan/Documents/coding/C/CustomShell
# cd dump
# cd
Current working dir: /Users/varunsrinivasan/Documents/coding/C/CustomShell/dump
# cd ..
# pwd
/Users/varunsrinivasan/Documents/coding/C/CustomShell
# ls -l > text.txt
# cat text.txt
total 32
-rw-r--r--@ 1 varunsrinivasan  staff  2975 Apr 24 10:35 Shell.c
drwxr-xr-x  2 varunsrinivasan  staff    68 Apr 24 10:36 dump
-rwxr-xr-x  1 varunsrinivasan  staff  9700 Apr 24 10:35 shell
-rw-r--r--  1 varunsrinivasan  staff     0 Apr 24 10:36 text.txt
# cat dump/dump1.txt dump/dump2.txt | sort
A
Friday
brownish
cloud
descends
every
growing
hovering
impressively
just
keeling
lightly
moving
nimbly
over
populated
quarters
# exit
