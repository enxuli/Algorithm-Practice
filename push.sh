#!/bin/sh

clear

HEAD="$(git rev-parse --abbrev-ref HEAD)"
echo "-----"


if [ "$HEAD" == master ]; then
echo "CHECKOUT YOUR OWN BRANCH FIRST"
say "checkout your own branch first, you shouldn't be on master branch"
else






git checkout master
already="$(git pull origin master)"
echo "-----"

if  [ "$already" = "Already up-to-date." ]; then


	if [ "$#" -ne 0 ]; then

		git merge "$HEAD" --no-ff -m "$*"
		echo "-----"

		git branch -d "$HEAD"
		echo "-----"

		git push -u origin master
		git checkout -b "$HEAD"
		git status
                say "push done"
	else
        	git checkout "$HEAD"

		echo "-----"
		echo "YOU FORGOT YOUR COMMIT MESSAGE !!!!"
		say " you forgot your commit message"
	fi


else
	git checkout "$HEAD"
	echo "-----"
	
	echo "REBASE FIRST !!!!!!"
        say "rebase first"

fi

unset already


fi
echo "-----"

unset HEAD
