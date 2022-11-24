#!/bin/bash

askPassword() {
	concept="$1"
	while true; do
		read -s -p "Password for $concept: " PASSWD;
		echo;
		read -s -p "Confirm password : " PASSWD_CONFIRM;
		echo;
		if [ "$PASSWD" = "$PASSWD_CONFIRM" ]; then
			break;
		fi
		echo "Passwords do not match";
		echo;
	done
}

SECRET_FILE=".env"

askData() {
	question="$1"
	default="$2"

	if [ ! "$default" = "" ]; then
		question="$question [$default]: ";
	fi

	read -p "$question" data

	if [ "$data" = "" ]; then
		data="$default"
	fi
}


askData "DB_NAME" "HR_App_DB"; DB_NAME="$data"
askData "DB_PORT" "5847"; DB_PORT="$data"
askData "DB_USR" "hr"; DB_USR="$data"
askPassword "$DB_USR"; DB_USR_PASSWD="$PASSWD"

echo "#!/bin/bash

DB_NAME='$DB_NAME'
DB_PORT='$DB_PORT'
DB_USR='$DB_USR'
DB_USR_PASSWD='$DB_USR_PASSWD'
" > $SECRET_FILE
