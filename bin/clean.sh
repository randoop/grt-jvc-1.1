#!/bin/sh

if [ $# != 2 ]; then
    echo "usage: clean.sh <top dir> <auto gen string>"
    exit -1
fi    

TOP_DIR=$1
AUTO_GEN_TAG=$2

if [[ "" != $AUTO_GEN_TAG ]] ; then
	echo "removing automatically generated code..."
	for x in `find $TOP_DIR -name '*.java'` ; do
		res=`fgrep "$AUTO_GEN_TAG" $x`
		if test -n "$res" ; then
		echo rm $x
			rm $x
		fi
	done
fi

echo "removing tilde files..."
for x in `find $TOP_DIR -follow -name '*~'` ; do
    echo rm $x
    rm $x
done

echo "finished cleaning"
