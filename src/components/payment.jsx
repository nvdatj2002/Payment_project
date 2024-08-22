import Button from 'react-bootstrap/Button';
import Card from 'react-bootstrap/Card';
import 'bootstrap/dist/css/bootstrap.min.css';
import { useNavigate, useParams, useSearchParams } from 'react-router-dom';
import { useEffect, useState } from 'react';
import callAPI from '../axios';
import Form from 'react-bootstrap/Form';
import Modal from 'react-bootstrap/Modal';

function Payment() {
    const [show, setShow] = useState(true);

    const handleClose = () => setShow(false);
    const handleShow = () => setShow(true);
    
    const [username,setUsername] = useState('');
    const [password,setPassword] = useState('');

    const navigate = useNavigate();
    const [banks, setBanks] = useState([]);
    const [infoPayment, setInforPayment] = useState({})
    const [isLoad, setIsLoad] = useState(false)
    const [idbank, setIdBank] = useState(1)
    
    let [searchParams] = useSearchParams('');

    const id = searchParams.get('id')

    useEffect(() => {
        fectAPI()
        console.log('1')
    }, [isLoad])
    
    const fectAPI = async () => {
        const response = await callAPI(`/api/payment?idO=${id}&idB=${idbank}`, 'GET');
        const responseBanks = await callAPI('/api/bank', 'GET');
        

        setInforPayment(response.data)
        setBanks(responseBanks)
        
    }
    const handleComfirmPay = async () => {

        const response = await callAPI('/api/payment/confirm', 'POST', infoPayment)
        if (response.status == 'ok') {
            alert('thanh toán thành công')
            navigate('/order');
        } else {
            alert('Thanh toán thất bại!')
        }
    }

    const handleLogin = (e) => {
        e.preventDefault();
        if (username == 'datnv' && password == '123') {
            handleClose()
            alert('Đăng nhập thành công')
        } else {
            alert('Tài khoản hoặc mật khẩu không đúng!')
        }

    }
    console.log(infoPayment)
    console.log(banks)
    return (
        <div>
            <div>
                <h2 style={{ marginBottom: '10px' }}>Thông tin thanh toán</h2>
                <Form.Select onChange={(e) => {
                    setIdBank(e.target.value)
                    setIsLoad(!isLoad)
                }} aria-label="Default select example">
                    {banks?.map((item, index) => (
                        <option key={index} value={item.id}>{item?.nameBank}</option>
                    ))}

                </Form.Select>
            </div>

            <Card style={{ minWidth: '18rem' }}>
                <Card.Img variant="top" src="holder.js/100px180" />
                <Card.Body>
                    <Card.Title>Nội dung chuyển khoản</Card.Title>
                    <div>
                        <div></div>
                        <p>Tên ngân hàng : <b>{infoPayment?.bank?.nameBank}</b></p>
                        <p>Số tài khoản : <b>{infoPayment?.bank?.numberBank}</b></p>
                        <p>Tên người nhận : <b>{infoPayment?.bank?.name}</b></p>
                        <p>Số tiền : <b>{infoPayment?.totalMoney}</b></p>
                        <p>Nội dung chuyển khoản : <b>{infoPayment?.content_transfer}</b></p>
                    </div>
                    <Button onClick={handleComfirmPay} variant="primary">Xác nhận thanh toán</Button>
                </Card.Body>
            </Card>
            <Modal
                show={show}

                backdrop="static"
                keyboard={false}
            >

                <Modal.Body>
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
                </Modal.Body>

            </Modal>
        </div>
    );
}

export default Payment;