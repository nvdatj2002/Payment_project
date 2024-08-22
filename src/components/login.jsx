import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

import 'bootstrap/dist/css/bootstrap.min.css';
import '../components/index.css'
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function Login({order}) {
    console.log(order)
    const [username,setUsername] = useState('');
    const [password,setPassword] = useState('');
    const navigate = useNavigate();
    const handleLogin = (e) => {
        e.preventDefault();
        if(username == 'datnv' && password == '123'){
            alert('Đăng nhập thành công')
            navigate('/payment');
        }else {
            alert('Tài khoản hoặc mật khẩu không đúng!')
        }
        
    }


    return (
        <>
            <Form className='form-login' >
                <Form.Group className="mb-3" controlId="formBasicEmail">
                    <Form.Label>Tên Đăng Nhập</Form.Label>
                    <Form.Control type="text" name='username' placeholder="Tên đăng nhập"
                        onChange={e => {
                            setUsername(e.target.value)
                        }}
                    />
                    <Form.Text className="text-muted">

                    </Form.Text>
                </Form.Group>
                <Form.Group className="mb-3" controlId="formBasicPassword">
                    <Form.Label>Mật khẩu</Form.Label>
                    <Form.Control name='password' type="password" placeholder="mật khẩu"
                    onChange={e => {
                        setPassword(e.target.value)
                    }}
                    />
                </Form.Group>

                <Button onClick={e => {
                    handleLogin(e)
                }} variant="primary" type="submit">
                    Đăng nhập
                </Button>
            </Form>
        </>
    );
}

export default Login;