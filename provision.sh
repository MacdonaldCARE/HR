echo "Installing packages..."
sudo apt update

# install apache2 silently
echo "Installing apache2..."
sudo apt install apache2 -y

# change default port to 8181 
#sudo sed -i 's/Listen 80/Listen 8181/g' /etc/apache2/ports.conf

# install java 11 silently
echo "Installing java 11..."
sudo apt install openjdk-11-jdk -y

# install python 3 silently
echo "Installing python 3..."
sudo apt install python3 -y

# install Django 4 
echo "Installing Django 4..."
sudo apt install python3-pip -y
sudo pip3 install django

# install psycopg2 silently
echo "Installing psycopg2..."
sudo apt install python3-psycopg2 -y

# install postgresql silently
echo "Installing postgresql..."
sudo apt install postgresql -y


# crreate a database in postgresql
echo "Creating a database..."
sudo -u postgres psql -c "CREATE DATABASE testdb;"

# creete a user in postgresql
echo "Creating a user..."
sudo -u postgres psql -c "CREATE USER root WITH PASSWORD 'root';"

# install Docker silently
echo "Installing Docker..."
sudo apt install docker.io -y

# create new user username tapiwa password root and add the user to postgresql group
echo "Creating new user..."


# install maven silently
echo "Installing maven..."
sudo apt install maven -y

cd /vagrant_data/apiPostgres

mvn spring-boot:run



