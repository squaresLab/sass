public class Plan1571775376045 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
if ( DecreaseTraffic("A") ) {
if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

} else {
StartServer("A");
}

for (int i = 0; i < 2 ; i++) {
StartServer("B");
}


StartServer("B");
if ( StartServer("A") ) {
StartServer("C");
} else {
StartServer("C");
}



StartServer("C");

} else {
StartServer("B");
}

}

}
}
