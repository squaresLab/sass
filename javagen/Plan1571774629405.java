public class Plan1571774629405 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 5 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("A");
}

} else {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
}

}

}

}
}
