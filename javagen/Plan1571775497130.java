public class Plan1571775497130 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

if ( StartServer("C") ) {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

} else {
for (int i = 0; i < 2 ; i++) {
StartServer("B");
}

}


if ( DecreaseTraffic("A") ) {
StartServer("C");
} else {
StartServer("C");
}


}

}
}
