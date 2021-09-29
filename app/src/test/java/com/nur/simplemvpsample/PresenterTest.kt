package com.nur.simplemvpsample

import com.nhaarman.mockitokotlin2.capture
import com.nhaarman.mockitokotlin2.verify
import com.nur.simplemvpsample.login.LoginContract
import com.nur.simplemvpsample.login.LoginPresenter
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentCaptor
import org.mockito.Captor
import org.mockito.Mock
import org.mockito.MockitoAnnotations


class PresenterTest {

    @Mock
    lateinit var view: LoginContract.View

    @Mock
    lateinit var presenter : LoginPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = LoginPresenter(view)
    }

    @Captor
       var captor: ArgumentCaptor<String> = ArgumentCaptor.forClass(String::class.java)

    @Test
     fun `login valid input success`(){

         presenter.login(validEmail, validPassword)
        verify(view).openNext()
        verify(view).showToast(capture(captor))
        Assert.assertEquals("Done", captor.value);
     }

    @Test
    fun `login invalid input failed`(){
        presenter.login(invalidEmail, validPassword)
        verify(view).showToast(capture(captor))
        Assert.assertEquals("Something wrong!", captor.value);
    }
    @Test
    fun testIsLoggedIn(){
        presenter.setIsLoggedIn(true)
        val result = presenter.isLoggedIn()
        Assert.assertEquals(result, true)
    }
    @Test
    fun `validateEmail for valid email returns true`(){
        val result = presenter.validateEmail(validEmail)
        Assert.assertEquals(result, true)
    }
    @Test
    fun `validateEmail for empty string returns false`(){
        val result = presenter.validateEmail(emptyString)
        Assert.assertEquals(result, false)
    }
    @Test
    fun `validateEmail for invalid email returns false`(){
        val result = presenter.validateEmail(invalidEmail)
        Assert.assertEquals(result, false)
    }

    @Test
    fun `validatePassword for valid password returns true`(){
        val result = presenter.validatePassword(validPassword)
        Assert.assertEquals(result, false)
    }

    @Test
    fun `validatePassword for empty password returns false`(){
        val result = presenter.validatePassword(emptyString)
        Assert.assertEquals(result, false)
    }

    @Test
    fun `validatePassword for invalid password returns false`(){
        val result = presenter.validatePassword(invalidPassword)
        Assert.assertEquals(result, false)
    }

}