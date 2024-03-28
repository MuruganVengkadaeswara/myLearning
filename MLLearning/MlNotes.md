## Data preprocessing
=====================

1 .importing libraries
2 .importing data set
3 .taking care of missing data
encoding categorical data

    encoding dependent variable
    encoding independent variable

Splitting dataset to training and testing set

feature scaling

Data preprocessing tools
=========================

    importing libraries 
        -> numpy
        -> matplotlib
        -> pandas

    numpy ->  work with arrays
    pandas -> will read from csv (assuming) as dataframe



Dependent variables and independent variables

dependent variable -> predicted variable

last column is the predicted / dependent variable

features are the columns with which we are gonna predict the dependent variable

x -> matrix of features
y -> dependent variable vector


iloc => locate indexes from pandas dataframe

[:] => take all rows
[:,]=> specify the columns after comma
[: , :-1] => take all columns except last

the range in python includes lower bound
excludes upper bound

## Taking care of missing data
===============================

Ways to handle :

    -> deleting the data / row (might work in place of large data set)

    -> replace the missing data by average of all the feature values


use scikitlearn -> to do this name => sklearn

using SimpleImputer





always upper bound is excluded

so if 2 columns -> use 3



from sklearn.impute import SimpleImputer
imputer = SimpleImputer(missing_values=np.nan , strategy='mean')
imputer.fit(x[:,1:3])
x[:,1:3] = imputer.transform(x[:,1:3])


dataset.isna().sum()

isna() method will find the nan values with column names

## Encoding catergorical data:
==============================

The non-numerical values are basically converted to numerical values

OneHot encoding :


fit_transform doesnt give numpy so force it



from sklearn.compose import ColumnTransformer
from sklearn.preprocessing import OneHotEncoder
ct = ColumnTransformer(transformers=[('encoder' , OneHotEncoder() ,[0])] , remainder='passthrough')
x = np.array(ct.fit_transform(x))

## Encoding dependent variables

label encoding=> binary values
???


from sklearn.preprocessing import LabelEncoder
le = LabelEncoder()
le.fit_transform(y)


## Splitting the data set to training and test data set





Do we have to apply feature scaling before / after splitting?

    -> After splitting is right

why ? -> test set is supposed to be a brand new set 
it should not leak data to test set


preventing information leakage in to test set until training is done



Feature scaling is used to normalise the range of independent variables


making sure all features takes same scale?

from sklearn : 
------------

    train test split method

    it will create 4 separate sets

    X train - independent
    y Train - dependent
    X test  - independent
    y test -  dependent

Code :

from sklearn.model_selection import train_test_split


pass data set as combination of matrix of features and y


80 % in training set 20 % in test set

train_test_split(x,y,test_size=0.2,)


code :

from sklearn.model_selection import train_test_split
x_train,x_test,y_train,y_test = train_test_split(x,y,test_size=0.2,random_state=1)



## Feature scaling:
=====================

Feature scaling techniques :

    1. Standardisation
    2. Normalisation

 X(stand) = x-mean(x) / standard deviation
(values bw -3 and 3)

X (norm) = x - min(x) / max(x) - min(X)


all values will be 0 to 1



should we go for standardisation or normalisation ?


Standardisation will work all the time

normalisation is good when we have normal distribution of features


Standardisation is recommended


feature scaling is done on x_train and apply on x_test



Do we have to apply feature scaling to the dummy variables in the matrix of features?

No we dont need to apply feature scaling to dummmy variables (after transforming)
(say after encoding)

Standardisation transforms and well lose the code 


it might increase training performance in some cases
but not recommended (applying feature scaling to non numerical values)

it is recommended to apply feature scaling only to the numerical values




scaler.fit will generate mean and standard deviation values

transform will apply this formula


Data preprocessing template:

    import libraries
    import dataset
    split to train and test set
















## Regression:
==============


Welcome to Part 2 - Regression!


Regression models (both linear and non-linear) are used for predicting a real value, like salary for example. If your independent variable is time, then you are forecasting future values, otherwise your model is predicting present but unknown values. Regression technique vary from Linear Regression to SVR and Random Forests Regression.

In this part, you will understand and learn how to implement the following Machine Learning Regression models:

    Simple Linear Regression

    Multiple Linear Regression

    Polynomial Regression

    Support Vector for Regression (SVR)

    Decision Tree Regression

    Random Forest Regression












Simple linear regression:
=========================




    y = b0 + b1x1


y = dependent



ordinary least squares :
=======================


y^ = b0 + b1x1
sum(y - y^)2 is minimised (squared)
is the best line

training simple linear regression model on training set : 
===================================


from sklearn import linear regression model


Diff bw regression and classification :

Regression is predicting a continuos real value (like salary)

Classification is predicting a class or category (like class)


fit method from LinearRegression is used to train the model on the training set


regressor.fit(x_train , y_train)

this will train the model


method to predict new observations is predict method 

    regressor.predict()

This works well with linearly related data





## Multiple Linear Regression
===============================

y intercept -> is the point in y axis where the slope of the line passes


y = b + b1x1 + b2x2+ ... +bnXn

y = dependent variable

b = y intercept constant

b1 , bn => slope coefficient

x1 .. xn => independent variable





## Assumptions of linear regression 
===================================


Anscombe's quartet

we have to make sure the data set is fit to make linear regression 




5 assumption + extra check

1. linearity

    we have to check if the x and y has linear relation ship

2. Homoscedasticity

    equal variance


3. Multivariate normality

    normality of error distribution

4. Independece 

    no autocorrelation

    any kind of pattern

    e.g stock market (previous prices to future prices)

5. lack of multicollinearity

    predictors are not correlated with each other 


6. Outlier check

    if some data is lying outside
    check

Dummy variables 
----------------

    if there are non numerical values say categorical variable 
    (e.g states)

    in this case we have to create dummy variables for these

    e.g OneHot encoding
    from scikit learn    


    we should never include all the dummy variables

        its called dummy variable trap

    
    Dummy variable trap : 
    ------------------

    we should not include all the dummy variable

    whenever building one model 
    we have to omit one dummy variable


    if we have 9 dummy variables we have to include 8


    Understanding P value :
    -----------------------

    Statistical significance :

    are these results/insights 
    significant or not



    in a universe of fair coin

    the probablity of the coin giving 6 tails / heads in a row will be decreasing 

    this is called p values

    in unfair universe

        the P values will be different 

    
    Statistical significance is 0.05 
    or 5 percent


    depending on experiment or case 
    we set value to 99% in case of medical data


    Garbage in = garbage out


    5 methods of building models:
    ----------------------------

        1.All in
        2.Backward elimination
        3.Forward selection
        4.Bidirectional elimination
        5.Score comparision



2. Backward elimination

   1. select significance level 
    e.g 0.5

   2. Fit full model with all possible predictors

   3. consider predicter with highest P value

        if(p > SL)  step 4 else FIN

        FIN -> model is ready

   4. Remove the predictor 

   5. Fit model without this variable

   loop 3 to 5

3. Forward selection

    1. select SL to enter the model

    2. Fit all simple regression models y~Xn select one with lowest P value

    3. Keep this variable and fit all possible models with one extra predictory added to one(s) we already have

    4. Consider the predictor with lowest P value 
    if p < SL go to step 3 else FIN

    (loop 3 and 4)

    FIN is keeping previous model


4. Bidirectional elimination

    1. select SL to enter model 
        SLENTER and SLSTAY both (0.05)

    2. perform next step of forward selection (new variables must have P < SLENTER to enter)

    3. perform all steps of backward elimination (old variables must stay P < SLSTAY to stay)

    loop 2 and 3

    4. No new variables can enter and no old variables can exit

    FIN -> model is ready


5. Score comparison

All possible models

    1. select goodness criterion
    2. Construct all possible regression models 2pow n -1 total combinations

    3. select one best criterion

    FIN

    if we have 10 cols we have 1023 models ????





Best one is to do Backward elimination




After onehotencoding the encoded values are kept at the beginning

in multiple linear regression : 

    we dont need to apply feature scaling 

    since we have multiple  coefficients


Do we need to check the assumptions of linear regression = no




Do we have to work on features to select best one and select features with highest p value and statistical significance?


No 

The class for multiple linear regression will take care of that 




This class also takes care of the dummy variable trap


As I explained in the previous tutorial, Backward Elimination is irrelevant in Python, because the Scikit-Learn library automatically takes care of selecting the statistically significant features when training the model to make accurate predictions.


https://www.dropbox.com/sh/pknk0g9yu4z06u7/AADSTzieYEMfs1HHxKHt9j1ba?dl=0


Just keep this for this Backward Elimination implementation, but keep in mind that in general you don't have to remove manually a dummy variable column because Scikit-Learn takes care of it.

# Building the optimal model using Backward Elimination

import statsmodels.api as sm
X = np.append(arr = np.ones((50, 1)).astype(int), values = X, axis = 1)
X_opt = X[:, [0, 1, 2, 3, 4, 5]]
X_opt = X_opt.astype(np.float64)
regressor_OLS = sm.OLS(endog = y, exog = X_opt).fit()
regressor_OLS.summary()X_opt = X[:, [0, 1, 3, 4, 5]]
X_opt = X_opt.astype(np.float64)
regressor_OLS = sm.OLS(endog = y, exog = X_opt).fit()
regressor_OLS.summary()X_opt = X[:, [0, 3, 4, 5]]
X_opt = X_opt.astype(np.float64)
regressor_OLS = sm.OLS(endog = y, exog = X_opt).fit()
regressor_OLS.summary()X_opt = X[:, [0, 3, 5]]
X_opt = X_opt.astype(np.float64)
regressor_OLS = sm.OLS(endog = y, exog = X_opt).fit()
regressor_OLS.summary()X_opt = X[:, [0, 3]]
X_opt = X_opt.astype(np.float64)regressor_OLS = sm.OLS(endog = y, exog = X_opt).fit()
regressor_OLS.summary()


https://www.superdatascience.com/pages/ml-regression-bonus-2

## Polynomial regression
=========================








## Classification: 
==================



Unlike regression where you predict a continuous number, you use classification to predict a category. There is a wide variety of classification applications from medicine to marketing. Classification models include linear models like Logistic Regression, SVM, and nonlinear ones like K-NN, Kernel SVM and Random Forests.

In this part, you will understand and learn how to implement the following Machine Learning Classification models:

    Logistic Regression
    K-Nearest Neighbors (K-NN)
    Support Vector Machine (SVM)
    Kernel SVM
    Naive Bayes
    Decision Tree Classification
    Random Forest Classification





Classification is a machine learning technique to identify a category of new observations based on training data


Customers of business 

likely to stay Vs like to leave


E.g Email classification

    Important Spam Promotions etc




Image recognition : 

    Dogs , Cats etc





## Logistic Regression Intuition
=================================

Predict a catergorical DV from number of IV


ln(p/1-p) = b0 + b1x1

Logistic regression curve also called as sigmoid curve

E.g Will purchase insurance based on Age?


Just like linear regression this also has multiple IV




ln(p/1-p) = b0 + b1x1 + b2x2


# Maximum Likelihood
--------------------


maximum likelihood is calcluated by 
multiplying all the likelihood values

e.g it there is yes / No prediction
we get likelihood as probablity from p1 to pn and for No we get 
1-p1 to 1-pn .

iteratively the likelihood is calculated for every curve

best curve <= maximum likelhood


Use case :

    Age , salary -> independent var
    purchased    -> Dependent var


    2 features 


    in example we are using 0.25 test size since we have 400 data




from sklearn.linear model import logistic regression

sklearn website -> API ref


Logistic regression model is a linear classifier 
so the classification is straight line


in  logistic regression the classification curve is a straight line

we need to get a classification curve (we can get this using non linear classifier)




get help here:  (this is simple)
===================================
https://www.superdatascience.com/blogs/the-ultimate-guide-to-regression-classification







https://www.udemy.com/course/logistic-regression-cancer-detection-case-study/?referralCode=7E62BC258B645C95D9F5




K-Nearest Neighbors:
====================

KNN intuition


    2 categories

    CAT1 , CAT2


    STEP 1 :

    choose number of K neighbours

    STEP 2 :

    take k nearest neighbors of new datapoint , according to eucledian distance

    STEP3 :

    among these k neighbors count the number of data points in each category

    STEP 4:

    assign new data point to category where you counted most neighbours



    MODEL Ready


K-NN algorithm


    eucledian distance bw p1 and p2 

    root((x2-x1)2 + (y2-y1)2)

Geometrical distance




from sklearn.neighbors import KNeighborsClassifier
classifier = KNeighborsClassifier(n_neighbors = 5, metric = 'minkowski', p = 2)
classifier.fit(x_train, y_train)





KNN model is very compute intensive





SVM intuition
==============

Support vector machines


    we have 2 categories

    we need to create a boundary between this 2 category

    when new points are added this will be classified on one of these categories based on this line or boundary

    SVM searches for this line
   
    i.e maximum margin

    this line is searched through maximum margin

    the sum of the 2 categorical points has to be maximised (ie limit)

    these points are called vectors

    supporting vectors

    maximum margin hyperplane
    maximum margin classifier




whats so spl about SVMs?
=========================


case : classify apple v orange

positive hyperplane -> apple
-ve                 -> orange

the machine would look for most apply apple / apples with apple like features


the support vector falls where most apple looking orange and most orange looking apple

these are support vectors

it looks at the very extreme case and analyses


so at times they perform much better than non support vector algorithm



from sklearn.svm 
use SVC and choose linear non linear




Kernel SVM:
===========


Kernel SVM intuition

if the data set / points are inseperable or non catergorisable

i.e the data is not linearly seperable

we have to extract a class

Higher Dimensional Space

in this case -> we add an extra dimension to our data and make it linearly separable


Mapping to a higher dimension :
--------------------------------

    in a 1D space the seperator / margin is a dot

    maximum margin hyperplane

    increase the dimension 

    e.g => 
    f = x-5
    becoming

    f=square(x-5)

    mapping to a higher dimensional space is highly compute intensive




Kernel Trick :
--------------

Gaussian RBF kernel

    fn (x, l) = exp -(x-l / 2 * square(sigma))



Types of kernel functions :
----------------------------

1. Gaussian RBF  (radial basis fn)
2. sigmoid kernel 
3. polynomia kernel



Non linear SVR 
--------------

SVR intuition:

    

Non linear support vector regression













Natural Language Processing
============================


NLP is applying machine learning models to text and language.


E.g predict review is good or bad using NLP.


NLP on a book to predict the genre of the book. 


most of NLP algorithms are classification models, and they include Logistic Regression, Naive Bayes,CART which is a model based on decision trees, Maximum Entropy again related to Decision Trees, Hidden Markov Models which are models based on Markov processes.




A very well-known model in NLP is the Bag of Words model. 

In this part, you will understand and learn how to:

    Clean texts to prepare them for the Machine Learning models,
    
    Create a Bag of Words model,

    Apply Machine Learning models 
    onto this Bag of Worlds model.


NLP intuition :
---------------

    Types of NLP :




    Classical Vs Deep learning models:


    1.if / else rules (chatbot) NLP

        set of predetermined        questions and answers


    2. audio frequency component analysis (speech recognition) NLP

        sound waves 

    3. Bag of words model NLP
        (classsification)


        How often the word is associated with the result 
        
        e.g review

    4. CNN for text recognition (classification) DNLP

        used for Video / image processing

        Convolutional Neural Network

    
    5. Seq2Seq (many applications)

        h0 -> h1 -> h2 -> h3 -> hn - g0 -> g1 -> g2

        h -> encoder
        g -> decoder



Bag of words Model:

    The video example had an email that requires 

    Create a model that gives Yes / No

    Start with vector with 20k elements with 0

    [0,0,0,0 .... 0]

    171,476 words are in dictionary

    out of this 3k words are used from total words


    because 20k words are used by average native test takers

    1st - SOS
    2nd - EOS

    Last -> special words -> words not encountered in english

    e.g name etc.

    Now the text is thrown in to bag of words .. i.e reconstructed in to the vector

    commas also has position

    we put the quantity of words for every position

    [1,1,0,0, 2]

    in  position 5 we have 2 if 



    each training data is converted to vectors of 20k length

    one of the model is logistic regression

    

































































