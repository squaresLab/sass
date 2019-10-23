public class Plan1571767957314 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 2 ; i++) {
for (int i = 0; i < 3 ; i++) {
StartServer("B");
if ( StartServer("C") ) {
DecreaseTraffic("A");
} else {
StartServer("A");
}


}

}

}
}
