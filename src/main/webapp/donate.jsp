<%
    if(session.getAttribute("email") == null){
    response.sendRedirect("login.jsp");
    return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Donation Form</title>
     <link rel="stylesheet" href="style.css">
     <link rel="stylesheet" href="donate.css">
</head>
<body>
 <nav class="navbar">
        <div class="logo">Care<span>Foundation</span></div>
        <div class="nav-links">
            <a href="index.jsp" >Home</a>
            <a href="about.jsp">About Us</a>
            <a href="contact.jsp">Contact</a>
            <a href="campaign?action=list">Campaigns</a>
            <a href="donate.jsp"class="active" class="donate-btn">Donate Now</a>
            <button onclick="window.location.href='login.jsp'" class="login-btn">Login</button>
        </div>
        <div class="hamburger">
            <span></span>
            <span></span>
            <span></span>
        </div>
 </nav>
    <div class="donation-container">
        <div class="progress-bar">
            <div class="progress" style="width: 33%"></div>
        </div>

        <!-- Step 1: Personal Information -->
        <div class="form-step active" id="step1">
            <h2>Personal Information</h2>
            <div class="form-group">
                <label for="firstName">First Name</label>
                <input type="text" id="firstName" required>
                <div class="error-message" id="firstNameError"></div>
            </div>
            <div class="form-group">
                <label for="lastName">Last Name</label>
                <input type="text" id="lastName" required>
                <div class="error-message" id="lastNameError"></div>
            </div>
            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" required>
                <div class="error-message" id="emailError"></div>
            </div>
            <div class="form-group">
                <label for="phone">Phone Number</label>
                <input type="tel" id="phone" required>
                <div class="error-message" id="phoneError"></div>
            </div>
            <div class="button-group">
                <button class="next-btn" onclick="nextStep(1)">Next</button>
            </div>
        </div>

        <!-- Step 2: Donation Amount -->
        <div class="form-step" id="step2">
            <h2>Select Amount</h2>
            <div class="amount-options">
                <button class="amount-btn" data-amount="100">100</button>
                <button class="amount-btn" data-amount="500">500</button>
                <button class="amount-btn" data-amount="1000">1000</button>
                <button class="amount-btn" data-amount="2000">2000</button>
                <button class="amount-btn" data-amount="5000">5000</button>
                <button class="amount-btn" data-amount="custom">Custom</button>
            </div>
            <div class="custom-amount form-group">
                <label for="customAmount">Enter Amount</label>
                <input type="number" id="customAmount" min="1">
                <div class="error-message" id="amountError"></div>
            </div>
            <div class="button-group">
                <button class="prev-btn" onclick="prevStep(2)">Previous</button>
                <button class="next-btn" onclick="nextStep(2)">Next</button>
            </div>
        </div>

        <!-- Step 3: Payment Method -->
        <div class="form-step" id="step3">
            <h2>Payment Method</h2>
            <div class="payment-methods">
                <div class="payment-method" data-method="card">Credit Card</div>
                <div class="payment-method" data-method="paypal">PayPal</div>
                <div class="payment-method" data-method="upi">UPI</div>
            </div>

            <!-- Credit Card Fields -->
            <div class="payment-fields card-fields" style="display: none;">
                <div class="form-group">
                    <label for="cardNumber">Card Number</label>
                    <input type="text" id="cardNumber" maxlength="16" required>
                    <div class="error-message" id="cardNumberError"></div>
                </div>
                <div class="form-group">
                    <label for="cardName">Name on Card</label>
                    <input type="text" id="cardName" required>
                    <div class="error-message" id="cardNameError"></div>
                </div>
                <div style="display: grid; grid-template-columns: 2fr 1fr; gap: 10px;">
                    <div class="form-group">
                        <label for="expiry">Expiry Date</label>
                        <input type="text" id="expiry" placeholder="MM/YY" required>
                        <div class="error-message" id="expiryError"></div>
                    </div>
                    <div class="form-group">
                        <label for="cvv">CVV</label>
                        <input type="text" id="cvv" maxlength="3" required>
                        <div class="error-message" id="cvvError"></div>
                    </div>
                </div>
            </div>

            <!-- PayPal Fields -->
            <div class="payment-fields paypal-fields" style="display: none;">
                <div class="form-group">
                    <label for="paypalEmail">PayPal Email</label>
                    <input type="email" id="paypalEmail" required>
                    <div class="error-message" id="paypalEmailError"></div>
                </div>
            </div>

            <!-- UPI Fields -->
            <div class="payment-fields upi-fields" style="display: none;">
                <div class="form-group">
                    <label for="upiId">UPI ID</label>
                    <input type="text" id="upiId" placeholder="example@upi" required>
                    <div class="error-message" id="upiIdError"></div>
                </div>
            </div>

            <div class="button-group">
                <button class="prev-btn" onclick="prevStep(3)">Previous</button>
                <button class="submit-btn" onclick="submitDonation()">Donate Now</button>
            </div>
        </div>
    </div>

    <script>
        let currentStep = 1;
        let selectedAmount = 0;
        let selectedPaymentMethod = '';
        let amountSelected = false; // Track if amount has been selected in Step 2


        // Function to display error messages
        function displayError(elementId, message) {
            document.getElementById(elementId).textContent = message;
        }

        // Function to clear error messages
        function clearError(elementId) {
            document.getElementById(elementId).textContent = '';
        }

        // Handle Amount Selection
        document.querySelectorAll('.amount-btn').forEach(btn => {
            btn.addEventListener('click', function() {
                document.querySelectorAll('.amount-btn').forEach(b => b.classList.remove('active'));
                this.classList.add('active');
                amountSelected = true;

                const amount = this.dataset.amount;
                if (amount === 'custom') {
                    document.querySelector('.custom-amount').style.display = 'block';
                    selectedAmount = document.querySelector('#customAmount').value;
                    console.log(selectedAmount);
                } else {
                    document.querySelector('.custom-amount').style.display = 'none';
                    selectedAmount = amount;
                    console.log(selectedAmount);
                }
            });
        });

        // Handle Payment Method Selection
        document.querySelectorAll('.payment-method').forEach(method => {
            method.addEventListener('click', function() {
                document.querySelectorAll('.payment-method').forEach(m => m.classList.remove('active'));
                this.classList.add('active');

                selectedPaymentMethod = this.dataset.method;
                document.querySelectorAll('.payment-fields').forEach(field => {
                    field.style.display = 'none';
                });
                document.querySelector(`.${selectedPaymentMethod}-fields`).style.display = 'block';
            });
        });

        function validateStep1() {
            let isValid = true;

            // Validate First Name
            const firstName = document.querySelector('#firstName').value;
            if (!firstName) {
                displayError('firstNameError', 'First Name is required');
                isValid = false;
            } else {
                clearError('firstNameError');
            }

            // Validate Last Name
            const lastName = document.querySelector('#lastName').value;
            if (!lastName) {
                displayError('lastNameError', 'Last Name is required');
                isValid = false;
            } else {
                clearError('lastNameError');
            }

            // Validate Email
            const email = document.querySelector('#email').value;
            if (!email) {
                displayError('emailError', 'Email is required');
                isValid = false;
            } else if (!isValidEmail(email)) {
                displayError('emailError', 'Please enter a valid email');
                isValid = false;
            } else {
                clearError('emailError');
            }

            // Validate Phone Number
            const phone = document.querySelector('#phone').value;
            if (!phone) {
                displayError('phoneError', 'Phone Number is required');
                isValid = false;
            } else {
                clearError('phoneError');
            }

            return isValid;
        }

        function validateStep2() {
            let isValid = true;

            // Check if an amount button has been selected or if a custom amount has been entered
            if (!amountSelected && document.querySelector('#customAmount').value === '') {
                displayError('amountError', 'Please select an amount to donate');
                isValid = false;
            } else {
                clearError('amountError');
            }

            return isValid;
        }

        function validateStep3() {
            let isValid = true;

            if (!selectedPaymentMethod) {
              alert('Please select a payment method.');
              return false; // Stop the function from proceeding further
             }

            // Validation for Credit Card
            if (selectedPaymentMethod === 'card') {
                const cardNumber = document.querySelector('#cardNumber').value;
                const cardName = document.querySelector('#cardName').value;
                const expiry = document.querySelector('#expiry').value;
                const cvv = document.querySelector('#cvv').value;

                if (!cardNumber) {
                    displayError('cardNumberError', 'Card Number is required');
                    isValid = false;
                } else {
                    clearError('cardNumberError');
                }

                if (!cardName) {
                    displayError('cardNameError', 'Name on Card is required');
                    isValid = false;
                } else {
                    clearError('cardNameError');
                }

                if (!expiry) {
                    displayError('expiryError', 'Expiry Date is required');
                    isValid = false;
                } else {
                    clearError('expiryError');
                }

                if (!cvv) {
                    displayError('cvvError', 'CVV is required');
                    isValid = false;
                } else {
                    clearError('cvvError');
                }
            }

            // Validation for PayPal
            if (selectedPaymentMethod === 'paypal') {
                const paypalEmail = document.querySelector('#paypalEmail').value;

                if (!paypalEmail) {
                    displayError('paypalEmailError', 'PayPal Email is required');
                    isValid = false;
                } else {
                    clearError('paypalEmailError');
                }
            }

            // Validation for UPI
            if (selectedPaymentMethod === 'upi') {
                const upiId = document.querySelector('#upiId').value;

                if (!upiId) {
                    displayError('upiIdError', 'UPI ID is required');
                    isValid = false;
                } else {
                    clearError('upiIdError');
                }
            }

            return isValid;
        }

        // Email validation function
        function isValidEmail(email) {
            const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
            return emailRegex.test(email);
        }

        function nextStep(step) {
            let isValid = true;

            if (step === 1) {
                isValid = validateStep1();
            } else if (step === 2) {
                isValid = validateStep2();
            }

            if (isValid) {
                document.querySelector(`#step${step}`).classList.remove('active');
                document.querySelector(`#step${step + 1}`).classList.add('active');
                document.querySelector('.progress').style.width = `${(step + 1) * 33}%`;
                currentStep = step + 1;
                // Reset the 'amountSelected' flag when entering Step 2
                if (step === 1) {
                  amountSelected = false;
                }
            } else {
                 return; // Prevents moving to the next step
             }
        }


        function prevStep(step) {
            document.querySelector(`#step${step}`).classList.remove('active');
            document.querySelector(`#step${step - 1}`).classList.add('active');
            document.querySelector('.progress').style.width = `${(step - 1) * 33}%`;
            currentStep = step - 1;
        }

        function submitDonation() {
            if(validateStep3()) {
                 // Collect form data
                const formData = {
                    firstName: document.querySelector('#firstName').value,
                    lastName: document.querySelector('#lastName').value,
                    email: document.querySelector('#email').value,
                    phone: document.querySelector('#phone').value,
                    amount: selectedAmount,
                    paymentMethod: selectedPaymentMethod,
                    // Add payment-specific fields based on selectedPaymentMethod
                };

                // Here you would typically send the data to your server
                console.log('Donation submitted:', formData);
                alert('Thank you for your donation!');
            }

        }

    </script>

    <jsp:include page="includes/footer.jsp"/>
</body>
</html>