package main

import (
	"context"
	"fmt"
	"log"

	"go.mongodb.org/mongo-driver/bson"
	"go.mongodb.org/mongo-driver/mongo"
	"go.mongodb.org/mongo-driver/mongo/options"

	"github.com/magiconair/properties"
)

type Lead struct {
	FirstName string
	LastName  string
	Seniority int
}

func main() {

	// init from a file
	props := properties.MustLoadFile("application.properties", properties.UTF8)
	// get values through getters
	mongodburi := props.MustGetString("mongodb.uri")

	// Set client options
	clientOptions := options.Client().ApplyURI(mongodburi)

	// Connect to MongoDB
	client, err := mongo.Connect(context.TODO(), clientOptions)

	// Check the connection
	err = client.Ping(context.TODO(), nil)
	if err != nil {
		log.Fatal(err)
	}

	fmt.Println("Connected to MongoDB!")

	// Get a handle for your collection
	collection := client.Database("showcase").Collection("leadGo")

	// Drop everything
	collection.Drop(context.TODO())

	// Some data to add to the Database
	insertData(collection)

	// Read all leads from database and iterates
	readAll(collection)

	// Find a single document
	findSingleDocument(collection)

	// Update a document
	updateDocument(collection)

	// delete a document
	deleteDocument(collection)

	// Close the connection once no longer needed
	err = client.Disconnect(context.TODO())

	if err != nil {
		log.Fatal(err)
	} else {
		fmt.Println("Connection to MongoDB closed.\n")
	}
}

func insertData(collection *mongo.Collection) {
	collection.InsertOne(context.TODO(), Lead{"Udo", "Peter", 25})
	collection.InsertOne(context.TODO(), Lead{"Christian", "Deinlein", 25})
	collection.InsertOne(context.TODO(), Lead{"Peter", "Witoschek", 10})
	collection.InsertOne(context.TODO(), Lead{"Philipp", "Bachmann", 5})
	collection.InsertOne(context.TODO(), Lead{"Dennis", "Peetz", 10})
	collection.InsertOne(context.TODO(), Lead{"Lieschen", "Müller", 35})
}

func readAll(collection *mongo.Collection) {
	fmt.Printf("Leads found with Find():\n")
	fmt.Printf("-------------------------------\n")
	findAll, err := collection.Find(context.TODO(), bson.M{})
	if err != nil {
		log.Fatal(err)
	}
	for findAll.Next(context.TODO()) {
		// To decode into a struct, use cursor.Decode()
		result := Lead{}
		err := findAll.Decode(&result)
		if err != nil {
			log.Fatal(err)
		}
		fmt.Printf("Document: '%s %s'\n", result.FirstName, result.LastName)

		// To get the raw bson bytes use cursor.Current
		// raw := findAll.Current
		// fmt.Printf("Document: %s\n", raw)
	}
	defer findAll.Close(context.Background())
	fmt.Printf("\n")
}

func updateDocument(collection *mongo.Collection) {
	fmt.Printf("Lead found with lastname('Peter'):\n")
	fmt.Printf("--------------------------------\n")
	filter := bson.M{"lastname": "Peter"}
	update := bson.M{
		"$set": bson.M{
			"firstname": "Stefan",
		},
	}
	updateResult, updateError := collection.UpdateOne(context.TODO(), filter, update)
	if updateError != nil {
		log.Fatal(updateError)
	}
	fmt.Printf("Matched %v documents and updated %v documents.\n\n", updateResult.MatchedCount, updateResult.ModifiedCount)
}

func findSingleDocument(collection *mongo.Collection) {

	fmt.Printf("Lead found with findByFirstName('Udo'):\n")
	fmt.Printf("--------------------------------\n")
	filter := bson.M{"lastname": "Peter"}
	var result Lead
	err := collection.FindOne(context.TODO(), filter).Decode(&result)
	if err != nil {
		log.Fatal(err)
	}
	fmt.Printf("Found a single document: %v\n\n", result)
}

func deleteDocument(collection *mongo.Collection) {
	fmt.Printf("delete Lead with firstname('Lieschen') and lastname('Müller'):\n")
	fmt.Printf("--------------------------------\n")
	filter := bson.D{
		{"$and", []bson.D{
			{{"firstname", "Lieschen"}},
			{{"lastname", "Müller"}},
		},
		}}
	deleteResult, deleteError := collection.DeleteOne(context.TODO(), filter)
	// deleteResult, deleteError := collection.DeleteOne(context.TODO(), Lead{"Lieschen", "Müller", 35})

	if deleteError != nil {
		log.Fatal(deleteError)
	}
	fmt.Printf("Deleted %v documents.\n\n", deleteResult.DeletedCount)
}
