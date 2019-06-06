# @author João Bolsson (jvmarques@gmail.com)

# Implementation of a SOM that indicates fraud in credit cards
# The SOM 

# Importing the libraries
import numpy as np
import matplotlib.pyplot as plt
import pandas as pd

# Importing the dataset
dataset = pd.read_csv('Credit_Card_Applications.csv')
# We will only use X because we are doin'g an unsupervisioned learning, no depend variable is considered
X = dataset.iloc[:, :-1].values # gets all the columsn excecpt the last one

# We don't predict if the costumers are approved or not, it just make the difference between
# the customers that were approved and those who weren't
Y = dataset.iloc[:, -1].values # gets the last column: approved = 1, not approved = 0

# Feature scaling
# Will be more easier to train our model if the features are scaled: normalization

from sklearn.preprocessing import MinMaxScaler
sc = MinMaxScaler(feature_range=(0, 1)) # feature will be between 0 and 1, that's normalization
X = sc.fit_transform(X) # normalize all the informations in X

# Training the SOM
from minisom import MiniSom # Importing the custom implementation of SOM
# x and y = 10, an arbitrary dimension
# input_len = 14 + 1 (14 features + 1 from custumerId to identify the outliers)
# sigma = 1.0 (is the radius of different neighbors in the grid)
# learning_rate is the how much the weights are updated (a big value produces a quick convergence)
som = MiniSom(x = 10, y = 10, input_len= 15)
# Randomly initializing of initial weights with values closes to zero but not zero
som.random_weights_init(X)

# the data to train and the number of interactions
som.train_random(X, 100)

# Visualizing the map with results
from pylab import bone, pcolor, colorbar, plot, show

# Initializing the figure that contains the map
bone()
pcolor(som.distance_map().T)# the matrix of all distances of the winning nodes
colorbar() # produces the legend of map with scales

# The frauds (outliers) are identified by bright colors
# That happens because the dark colors represents the winning nodes that had closes neighbors
# They are amounted in regions with high density, and this amount of closes nodes are identify by dark colors
# That means the clearer colors identifies the outliers.

# Putting markers on the map
markers = ['o', 's'] # circles and squares
colors = ['r', 'g'] # red and green

# For each node we will to mark who that was get approved and those who not
# red circle => not approval
# green square => approval

# That because we want to identify frauds on those who get the approval of credit card, that make more sense

# i for lines and X for vector that represents the customer
for i, x in enumerate(X):
    w = som.winner(x) # the winning node of customer x
    plot(w[0] + 0.5, 
         w[1] + 0.5,
         markers[Y[i]],
         markeredgecolor = colors[Y[i]],
         markerfacecolor = 'None',
         markersize=10,
         markeredgewidth=2) # +0.5 to plot on the center of square on the map

show()

# Finding the ouliers
mappings = som.win_map(X)

# Getting the list of cheaters
# We need to identify the coordinates of winning nodes that contains outliers (the clearers ones)
# We identify 2 nodes that contains possible cheaters (outliers)
# (5,1) and (3,7)
frauds = np.concatenate((mappings[(5,1)], mappings[(3,7)]))
frauds = sc.inverse_transform(frauds)

