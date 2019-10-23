public class Plan1571773989084 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

StartServer("B");

if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
StartServer("B");
} else {
StartServer("B");
}

} else {
StartServer("C");
StartServer("B");

}


}

}
}
