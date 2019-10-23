public class Plan1571773494199 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( StartServer("C") ) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

} else {
StartServer("C");
}

if ( StartServer("B") ) {
DecreaseTraffic("A");
} else {
StartServer("B");
}


}

}
}
