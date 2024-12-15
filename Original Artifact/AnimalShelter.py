from pymongo import MongoClient, errors
from bson.objectid import ObjectId


class AnimalShelter(object):
    """ CRUD operations for Animal collection in MongoDB """

    def __init__(self, user, pwd):
        # Initializing the MongoClient. This helps to 
        # access the MongoDB databases and collections.
        # This is hard-wired to use the aac database, the 
        # animals collection, and the aac user.
        # Definitions of the connection string variables are
        # unique to the individual Apporto environment.
        #
        # You must edit the connection variables below to reflect
        # your own instance of MongoDB!
        #
        # Connection Variables
        #
        USER = 'aacuser'
        PASS = 'SNHU1234'
        HOST = 'nv-desktop-services.apporto.com'
        PORT = 32596
        DB = 'AAC'
        COL = 'animals'
        #
        # Initialize Connection
        #
        try:
            self.client = MongoClient(f'mongodb://{USER}:{PASS}@{HOST}:{PORT}', serverSelectionTimeoutMS=25000)
            self.database = self.client['%s' % (DB)]
            self.collection = self.database['%s' % (COL)]
            print("Connected to Animal Shelter database.")
            
        except errors.ServerSelectionTimeoutError as err:
            print(f"Failed to connect to MongoDB: {err}")

# Complete this create method to implement the C in CRUD.
    def create(self, data):
        print("In create method...")
        
        if data is not None:
            if self.client is not None:
                result = self.database.animals.insert_one(data)  # data should be dictionary 
                return result.acknowledged
            else:
                raise Exception("Not connected to MongoDb")
        else:
            raise Exception("Nothing to save, because data parameter is empty")

            
# Create read method to implement the R in CRUD.

    def read(self,data):

        if data is not None:    
            data_read = self.database.animals.find_one(data,)
            return data_read
        else:
            raise Exception("Nothing to read because data parameter is empty/incorrectly formatted")

    def readAll(self, data):
        data_read = self.database.animals.find(data,{"_id":False})   
        return data_read
            
# Update method to implement the U in CRUD
    def update(self, searchTerm, updateData):
        print("In update method...")
        #Update document(s) in the collection
        if searchTerm is not None and updateData is not None:
            if self.client is not None:
                result = self.collection.update_many(searchTerm, {'$set': updateData})
                return result.modified_count
            else:
                raise Exception("Not connected to MongoDB")
        else:
            raise Exception("Search or update parameters are missing")
            
# Delete method to implement the D in CRUD
    def delete(self, searchTerm):
        print("In delete method...")
        #Remove document(s) from the collection
        if searchTerm is not None:
            if self.client is not None:
                result = self.database.animals.delete_many(searchTerm)
                return result.deleted_count
            else:
                raise Exception("Not connected to MongoDB")
        else:
            raise Exception("Nothing to delete. Search parameter is missing")
        
     