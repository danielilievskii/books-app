import {SignInForm} from "../../components/signIn/SignInForm.jsx";


function SignInPage() {

    return (
        <section className="pt-5 mt-5">
            <div className="container mt-5">
                <div className="row justify-content-center">
                    <div className="col-md-6">
                        <div className="floating-card p-5">
                            <h5 className="text-center fw-bold text-uppercase mb-4">Login</h5>
                            <div className="card-body">
                                <SignInForm/>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    );
}

export default SignInPage;