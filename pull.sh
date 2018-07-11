#!/bin/sh

clear

HEAD="$(git rev-parse --abbrev-ref HEAD)";
echo "-----"




if [ "$HEAD" == master ]; then
echo "CHECKOUT YOUR OWN BRANCH FIRST"
say "checkout your own branch first, you shouldn't be on master branch"
else






	git checkout master
	echo "-----"
	git pull origin master
	echo "-----"

	git checkout "$HEAD"
	echo "-----"
	git rebase master
	ANS="$(git status | sed -n 2p)";
	echo "-----"
	GUESS="nothing to commit, working tree clean"
	if [[ ( "$ANS"   == "Current branch "$HEAD" is up to date."  ) || ( "$ANS" == "$GUESS" ) ]] ; then
		clear
		git status
		echo "now run ./push.sh '+ message' "
                say "pull done, now push with message"
 
	else
		echo "ANS=""$ANS""END_ANS"
		say "maybe merge conflicts"
		#git status
		echo "merge conflicts (if any) then run ./push.sh '+ message' "
		echo " "
		echo " "
		git status
	fi
	unset GUESS
	unset ANS


fi
echo "-----"

unset HEAD
