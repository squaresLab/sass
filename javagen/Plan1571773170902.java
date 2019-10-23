public class Plan1571773170902 extends Plan { 
public static void main(String[] args) { 
for (int i = 0; i < 3 ; i++) {
for (int i = 0; i < 2 ; i++) {
StartServer("A");
}

}

DecreaseTraffic("A");
if ( DecreaseTraffic("A") ) {
StartServer("B");
DecreaseTraffic("A");

} else {
for (int i = 0; i < 3 ; i++) {
StartServer("A");
}

}



for (int i = 0; i < 4 ; i++) {
StartServer("C");
StartServer("B");

}


}
}
