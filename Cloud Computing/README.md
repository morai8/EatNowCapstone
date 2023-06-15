# EATNOW API

## What you need

1. Google Cloud Platform:
   - Compute Engine
   - Cloud Storage
   - VPC Network
   - Cloud SQL
2. Programming Language: Javascript
3. Web Server: Node JS

# Setup Locally

1. Clone the project first
2. Checkout to branch `capstone-api`
3. Install node_modules `npm install`
4. Run `index.js` for running the API script with `nodemon index` or `npm run start`

# Setup in Google Cloud Platform

## Create Compute Engine

1. VM Instance name
2. Choose N1 series and machine tipe `n1-standard-2 (2vCPU 7,5GB)`
3. Boot disk section
   - Operation system : Ubuntu
   - Version : 18.04 LTS
   - Boot disk type : SSD Persistent Disk
   - Size : 10 GB
4. Network custom VPC for zone`asia-southeast2-a(Jakarta)
5. Firewall : Allow HTTP Traffic
6. Create the VM Instances

## Create Cloud Storage

1. Click `Create Bucket`
2. Bucket name
3. Region `asia-southeast2 (Jakarta)`
4. Create the Bucket

## Create VPC & Firewall

1. VPC(Custom)
   - vpc name
   - custom subnet
   - internal ip range 10.0.1.0/24
2. Firewall
   - firewall name
   - target http-server
   - source IPv4 ranges = 0.0.0.0/0
   - Protocols and port > tcp = 22, 3306, 8080

### Setting up VM Instances for Production Server

1. Click `SSH` on VM Instances
2. After your already inside the SSH, you have to install some depedencies
   - `sudo apt-get update`
   - `sudo apt-get upgrade`
   - `sudo apt install curl`
   - `curl -sL https://deb.nodesource.com/setup_16.x | sudo -E bash -`
   - `sudo apt install nodejs`
   - Check version after install node v16 with `nodejs --version` & `npm –version`
3. Connect ssh vm with cloud sql
   - install client mysql `sudo apt-get install mysql-client`
   - connect to sql `mysql -h your_public_IP_address_SQL -u root -p`
   - create db `create database my_database;`
   - change config(conncetion) customize with cloud sql
4. Run APIs without opening SSH vm
   - install pm2 `npm install pm2 -g`
   - run pm2 `pm2 start npm --name Name_Api --watch -- run start`
   - cek pm2 `pm2 list`
