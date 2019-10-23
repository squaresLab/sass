public class Plan1571768768559 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

if ( StartServer("A") ) {
StartServer("B");
} else {
StartServer("A");
}


if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {

}


}

}
}
