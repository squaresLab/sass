public class Plan1571773111177 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("A");
}

} else {
DecreaseTraffic("A");
}

}

StartServer("B");

StartServer("A");
StartServer("B");
if ( StartServer("A") ) {
StartServer("C");
StartServer("B");

} else {
StartServer("B");
}



for (int i = 0; i < 3 ; i++) {
StartServer("C");
}


StartServer("B");


}
}
