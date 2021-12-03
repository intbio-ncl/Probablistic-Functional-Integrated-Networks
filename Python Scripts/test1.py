import re

import dash
import dash_core_components as dcc
import dash_html_components as html
from dash.dependencies import Input, Output

import numpy as np
import pandas as pd

column_names = ['DatasetsNames', 'V17', 'V23', 'V30', 'V36', 'V42', 'V48', 'V54', 'V60',
       'V66', 'V72', 'V78', 'V84', 'V92', 'V96', 'V102', 'V108', 'V114',
       'V120', 'V126', 'V132', 'V138', 'V144', 'V150', 'V156', 'V162', 'V166',
       'V168', 'V172', 'V174', 'V181']

data = pd.read_csv("LLSF.txt", delimiter="\t", names=column_names, keep_default_na=False)
# Dropping the values that don't have dates.
f = data["DatasetsNames"].str.contains(r"\([0-9]{4}\)")
print("Dropping:")
print(data[~f]["DatasetsNames"])
data = data[f]

data = data.replace(to_replace="null", value=-np.inf, regex=True)
for col in data.columns:
    if not col.startswith("V"):
        continue
    data[col] = data[col].astype(float)

print(data)


external_stylesheets = ['https://codepen.io/chriddyp/pen/bWLwgP.css']

app = dash.Dash(__name__, external_stylesheets=external_stylesheets)

dataset_options = [{'label': i, 'value': i} for i in data["DatasetsNames"]]
dataset_options.sort(key=lambda i:i['label'].split("(")[1].split(")")[0])

app.layout = html.Div(children=[
    html.Div([
        dcc.Dropdown(
            id='select_dataset',
            options=dataset_options,
            value=dataset_options[0]["value"]
        ),
    dcc.Graph(
        id='example-graph',
    )
    ]),


])

@app.callback(
    Output('example-graph', 'figure'),
    [Input('select_dataset', 'value'),]
)
def update_graph(select_dataset):
    print(select_dataset)
    row = data[ data["DatasetsNames"] == select_dataset ]
    tmp = dict(row.iloc[0])
    del tmp["DatasetsNames"]
    x = sorted(list(tmp.keys()), key=lambda i:int(i[1:]))
    y = [tmp[i] for i in x]
    y = [0 if np.isnan(i) else i for i in y ]
    
    print(y)

    return {
        "data": 
            [{"x":x, "y":y,}, {"x":x, "y":y, "mode":"markers", }],
            
        "layout": {
            "title": select_dataset,
            "yaxis": {
                "range": [
                    -1,
                    10
                ]
            },
            "showlegend": False
        }
    }

if __name__ == '__main__':
    app.run_server(debug=True)
