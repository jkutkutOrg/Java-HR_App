#!/bin/bash

askPassword() {
	concept="$1"
	while true; do
		read -s -p "Password for $concept: " PASSWD;
		echo;
		if [ "$PASSWD" = "" ]; then
			echo "The password must not be empty";
			continue;
		fi
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


echo "DB:";
askData "DB_NAME" "HR_App_DB"; DB_NAME="$data";
askData "DB_PORT" "5847"; DB_PORT="$data";
askData "DB_USR" "hr"; DB_USR="$data";
askPassword "$DB_USR"; DB_USR_PASSWD="$PASSWD";

echo;
echo "DB Web controller:";
askData "WEB_CONTROLLER_NAME" "HR_APP_DBController"; WEB_CONTROLLER_NAME="$data";
askData "WEB_CONTROLLER_PORT" "1250"; WEB_CONTROLLER_PORT="$data";
askData "WEB_CONTROLLER_EMAIL" "admin@admin.com"; WEB_CONTROLLER_EMAIL="$data";
askPassword "$WEB_CONTROLLER_EMAIL"; WEB_CONTROLLER_PASSWD="$PASSWD";

echo "#!/bin/bash

# DB
DB_NAME='$DB_NAME'
DB_PORT='$DB_PORT'
DB_USR='$DB_USR'
DB_USR_PASSWD='$DB_USR_PASSWD'

# DB Web controller
WEB_CONTROLLER_NAME='$WEB_CONTROLLER_NAME'
WEB_CONTROLLER_PORT='$WEB_CONTROLLER_PORT'
WEB_CONTROLLER_EMAIL='$WEB_CONTROLLER_EMAIL'
WEB_CONTROLLER_PASSWD='$WEB_CONTROLLER_PASSWD'
" > $SECRET_FILE
