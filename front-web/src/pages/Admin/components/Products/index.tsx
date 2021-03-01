import React from 'react';
import { Route, Switch } from 'react-router';

import Form from './Form';
import List from './List';

const Products = () => {
    return(
        <div>
            <Switch>
                <Route path="/admin/products" exact>
                <List />
                </Route>
                <Route path="/admin/products/create">
                <Form />
                </Route>
                <Route path="/admin/products/:productId">
                Editar Produto
                </Route>
            </Switch>
        </div>
    );
}

export default Products;