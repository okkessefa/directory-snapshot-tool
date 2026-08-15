# Directory Snapshot Tool

## Overview
What the program does.

## Features
- Recursive directory scanning
- SHA-256 hashing
- Snapshot persistence
- Added / deleted / modified / unchanged detection
- CLI commands
- Input validation
- Console reporting

## Requirements
- Java 17+
- Maven

## Build
mvn clean package

## Usage

### Create snapshot
java -jar ... snapshot <directory>

### Compare directory
java -jar ... compare <directory>

## Example
Example comparison output.

## Project Structure
application/
comparison/
hashing/
model/
persistence/
report/
scanner/

## Testing
mvn test

## How It Works
Directory → metadata → snapshot.json → later scan → comparison

## Version
v1.0.0